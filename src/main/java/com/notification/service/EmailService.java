package com.notification.service;

import freemarker.template.TemplateException;

import javax.mail.MessagingException;
import java.io.IOException;

public interface EmailService {

    public void sendEmail(UserInfo user) throws MessagingException, IOException, TemplateException;
}
