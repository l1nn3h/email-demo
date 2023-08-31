package com.example.email.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;

@Service
@Slf4j
public class EmailSenderService {

    @Value("${welcomeUrl}")
    private String welcomeUrl;

    private final TemplateEngine templateEngine;
    private final JavaMailSender mailSender;

    @Autowired
    public EmailSenderService(TemplateEngine templateEngine, JavaMailSender mailSender) {
        this.templateEngine = templateEngine;
        this.mailSender = mailSender;
    }

    public void sendSimpleWelcomeEmail(String recipient, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(recipient);
        message.setSubject("Simple Welcome");
        message.setText(body);
        mailSender.send(message);
        log.info("Simple welcome email successfully sent");
    }

    public void sendWelcomeMailFromTemplate(String email, String name) throws MessagingException {
        Context context = createContext(email, name);

        String process = templateEngine.process("emails/welcome", context);
        javax.mail.internet.MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        helper.setSubject("Welcome from Template");
        helper.setText(process, true);
        helper.setTo(email);
        mailSender.send(mimeMessage);
    }

    public void sendPrettyWelcomeMailFromTemplate(String email, String name) throws MessagingException {
        Context context = createContext(email, name);

        String process = templateEngine.process("emails/pretty-welcome", context);
        javax.mail.internet.MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        helper.setSubject("Welcome from Pretty Template");
        helper.setText(process, true);
        helper.setTo(email);
        mailSender.send(mimeMessage);
    }

    private Context createContext(String email, String name) {
        Context context = new Context();
        context.setVariable("email", email);
        context.setVariable("name", name);
        context.setVariable("welcomeurl", welcomeUrl);
        return context;
    }

}
