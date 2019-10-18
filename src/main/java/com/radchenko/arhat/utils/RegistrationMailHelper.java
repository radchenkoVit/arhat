package com.radchenko.arhat.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RegistrationMailHelper {
    private static String DOMAIN;
    private static String PORT;
    private static final String BODY_PATTERN = "Activation code: http://%s:%s/activate/%s";

    private RegistrationMailHelper() {
    }

    public static String getActivationBody(String code) {
        return String.format(BODY_PATTERN, DOMAIN, PORT, code);
    }

    @Value("${domain.name}")
    public void setDomain(String d) {
        DOMAIN = d;
    }

    @Value("${server.port}")
    public void setPort(String port){
        PORT = port;
    }

}
