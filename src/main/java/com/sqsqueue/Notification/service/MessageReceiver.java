package com.sqsqueue.Notification.service;

import com.google.gson.Gson;
import com.sqsqueue.Notification.dto.Event;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.aws.messaging.listener.Acknowledgment;
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

@Slf4j
@Service
public class MessageReceiver {

    @Autowired
    NotificationService notificationService;

    @SqsListener("MyFirstQueue.fifo")
    public void loadMessageFromSQS(String message) throws ClassNotFoundException {
        log.info("message from SQS Queue {}",message);
        String[] couple = message.split(",");
        Map<String,String> map1=new HashMap<>();
        for(String pair : couple)
        {
            String[] entry = pair.split("=");
            map1.put(entry[0].trim(), entry[1].trim());
        }

        notificationService.getData(map1.get("eventType"));
    }


}
