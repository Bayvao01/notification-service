package com.notification.service;

import com.notification.entity.Notification;
import com.notification.repository.NotificationRepository;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

@Service
public class EmailServiceImpl implements EmailService{

    @Autowired
    private Configuration configuration;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private NotificationRepository notificationRepository;

    @Override
    public void sendEmail(UserInfo user) throws MessagingException, IOException, TemplateException {

        Notification notification = notificationRepository.findById("TEST_EMAIL").get();
        String templateStr = notification.getTemplate();

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        helper.setSubject(notification.getSubject());
        helper.setTo(user.getEmail());
        String emailContent = getEmailContent(user, templateStr);
        helper.setText(emailContent, true);
        javaMailSender.send(mimeMessage);
    }

    String getEmailContent(UserInfo user, String templateStr) throws IOException, TemplateException {
        StringWriter stringWriter = new StringWriter();

        Template t = new Template("name", new StringReader(templateStr),
                this.configuration);

        Map<String, Object> model = new HashMap<>();
        model.put("user", user);

        t.process(model, stringWriter);


        //configuration.getTemplate("email.ftlh").process(model, stringWriter);
        return stringWriter.getBuffer().toString();
    }


}
