package com.radchenko.arhat.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Getter
@Configuration
@PropertySource("classpath:application.properties")
public class MailProperties {

    @Value("${spring.mail.username}")
    private String username;

    @Value("${domain.name}")
    private String domain;

    @Value("${server.port}")
    public String port;

    public String generateActivationCode(String code) {
        return String.format("http://%s:%s/activate/%s", domain, port, code);
    }
}
