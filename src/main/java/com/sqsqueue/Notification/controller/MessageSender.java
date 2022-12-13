package com.sqsqueue.Notification.controller;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.sqsqueue.Notification.dto.Event;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
public class MessageSender {

    @Autowired
    private QueueMessagingTemplate queueMessagingTemplate;

    @Value("${cloud.aws.end-point.uri}")
    private String endpoint;


    @GetMapping("/put")
    public void putMessagedToQueue(@RequestBody Event event) throws MessagingException {

        Message payload = MessageBuilder.withPayload(event)
                .setHeader("message-group-id", event.getMessageGroupId())
                .setHeader("message-deduplication-id", event.getMessageDedupId()).build();
        queueMessagingTemplate.send(endpoint,payload);
        log.info("Message sent to the Queue :{}",event);
    }



}
