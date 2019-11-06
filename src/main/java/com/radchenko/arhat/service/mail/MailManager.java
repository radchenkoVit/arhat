package com.radchenko.arhat.service.mail;

import java.util.Map;

public interface MailManager {
    void send(String emailTo, String subject, String template, Map<String, Object> model);
}
