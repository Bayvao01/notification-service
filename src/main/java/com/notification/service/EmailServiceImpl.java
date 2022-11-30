package com.notification.service;

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

    @Override
    public void sendEmail(UserInfo user) throws MessagingException, IOException, TemplateException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        helper.setSubject("Welcome To SpringHow.com");
        helper.setTo(user.getEmail());
        String emailContent = getEmailContent(user);
        helper.setText(emailContent, true);
        javaMailSender.send(mimeMessage);
    }

    String getEmailContent(UserInfo user) throws IOException, TemplateException {
        StringWriter stringWriter = new StringWriter();

        String templateStr= "<!doctype html>\n" +
                "<html lang=\"en\">\n" +
                "    <head>\n" +
                "        <meta charset=\"UTF-8\">\n" +
                "        <meta name=\"viewport\" content=\"width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0\">\n" +
                "        <meta http-equiv=\"X-UA-Compatible\" content=\"ie=edge\">\n" +
                "        <title>Spring Boot Email using FreeMarker</title>\n" +
                "    </head>\n" +
                "    <body>\n" +
                "        <div style=\"margin-top: 10px\">Greetings, ${user.name}</div>\n" +
                "        <div>Your username is <b>${user.username}</b></div>\n" +
                "        <br/>\n" +
                "        <div> Have a nice day..!</div>\n" +
                "    </body>\n" +
                "</html>";


        Template t = new Template("name", new StringReader(templateStr),
                this.configuration);

        Map<String, Object> model = new HashMap<>();
        model.put("user", user);

        t.process(model, stringWriter);


        //configuration.getTemplate("email.ftlh").process(model, stringWriter);
        return stringWriter.getBuffer().toString();
    }


}
