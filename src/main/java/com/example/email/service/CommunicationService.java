package com.example.email.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;

@Service
@Slf4j
public class CommunicationService {

    private final EmailSenderService emailSenderService;

    @Autowired
    public CommunicationService(EmailSenderService emailSenderService) {
        this.emailSenderService = emailSenderService;
    }

    public void sendSimpleWelcome(String email, String name) {
        String body = "Hello " + name + ",Welcome to your demo email!";
        emailSenderService.sendSimpleWelcomeEmail(email, body);
    }

    public void sendWelcomeMailFromTemplate(String email, String name) {
        try {
            emailSenderService.sendWelcomeMailFromTemplate(email, name);
            log.warn("Email successfully sent to {}, ", email);
        } catch (MessagingException e) {
            log.warn("Error sending email to {}, error: {}", email, e);
        }
    }

    public void sendPrettyWelcomeMailFromTemplate(String email, String name) {
        try {
            emailSenderService.sendPrettyWelcomeMailFromTemplate(email, name);
            log.warn("Email successfully sent to {}, ", email);
        } catch (MessagingException e) {
            log.warn("Error sending email to {}, error: {}", email, e);
        }
    }

}
