package com.radchenko.arhat.service.mail;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.mail.internet.MimeMessage;

@Slf4j
@Component
public class AsyncMailer {
    private JavaMailSender mailSender;

    @Autowired
    public AsyncMailer(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Async
    public void send(MimeMessage mailMessage) {
        try {
            mailSender.send(mailMessage);
        } catch (Exception e) {
            log.error("Failed to send message", e);
        }
    }

    public MimeMessage createMimeMessage() {
       return mailSender.createMimeMessage();
    }
}
