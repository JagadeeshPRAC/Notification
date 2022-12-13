package com.sqsqueue.Notification.service;

import com.sqsqueue.Notification.entity.Notify;
import com.sqsqueue.Notification.repository.SQSRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class NotificationService {

    @Autowired
    SQSRepository sqsRepository;

    public void getData(String eventType){
        List<Notify> notifyList=sqsRepository.getData(eventType);
        for(int i=0;i<notifyList.size();i++){
            String channel=notifyList.get(i).getChannel();
            if(channel.equalsIgnoreCase("whatsapp")) {
                log.info("Message sent through whatsapp of body :{}",notifyList.get(i).getBody());
            } else if (channel.equalsIgnoreCase("email")) {
                log.info("Message sent through email of body :{}",notifyList.get(i).getBody());
            } else if (channel.equalsIgnoreCase("sms")) {
                log.info("Message sent through sms of body :{}",notifyList.get(i).getBody());
            }
        }

    }
}
