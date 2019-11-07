package com.radchenko.arhat.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

@Getter
@ConfigurationProperties
@PropertySource("classpath:application.properties")
@Validated
public class MailProperties {

    @Value("${spring.mail.username}")
    @NotBlank
    private String username;

    @Value("${domain.name}")
    @NotBlank
    private String domain;

    @Value("${server.port}")
    @NotBlank
    public String port;

    public String generateActivationCode(String code) {
        return String.format("http://%s:%s/activate/%s", domain, port, code);
    }
}
