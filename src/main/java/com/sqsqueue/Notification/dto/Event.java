package com.sqsqueue.Notification.dto;


import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Event {
    private String leadId;
    private String eventType;

    private String messageGroupId;
    private String messageDedupId;


}
