package com.radchenko.arhat.service.mail;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.util.HashMap;
import java.util.Map;

@Service
public class MailSenderService {

    @Value("${spring.mail.username}")
    private String username;

    private final JavaMailSender mailSender;
    private final Configuration freemakerConfiguration;

    @Autowired
    public MailSenderService(JavaMailSender mailSender, Configuration freemakerConfiguration) {
        this.mailSender = mailSender;
        this.freemakerConfiguration = freemakerConfiguration;
    }

    public void sendActivationEmail() {

    }

    public void send(String emailTo, String subject, String messageBody) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();


        mailMessage.setFrom(username);
        mailMessage.setTo(emailTo);
        mailMessage.setSubject(subject);
        mailMessage.setText(messageBody);

        mailSender.send(mailMessage);
    }

//    private String createMessageBody(String templateName, MessageVariable... variables) {
//        try {
//            Template template = freemakerConfiguration.getTemplate(templateName);
//            Map<String, Object> model = new HashMap<>();
//            if (variables != null) {
//                for (MessageVariable variable : variables) {
//                    model.put(variable.getKey(), variable.getValue());
//                }
//            }
//            return FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
//        } catch (Exception e) {
//            log.error("Failed to create message body from template `" + templateName + "`", e);
//            return null;
//        }
//    }
}
