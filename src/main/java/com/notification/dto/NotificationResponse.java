package com.notification.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class NotificationResponse {

    private String name;

    private String subject;

    private String template;
}
