package com.notification.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/v1/users/register")
    public String register(@RequestBody UserInfo userInfo) throws Exception {
        emailService.sendEmail(userInfo);
        return "Email Sent..!";
    }
}
