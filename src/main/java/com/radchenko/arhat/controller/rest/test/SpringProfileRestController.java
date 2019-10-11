package com.radchenko.arhat.controller.rest.test;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/profile")
public class SpringProfileRestController {

    @Value("${current.profile}")
    private String profile;

    @GetMapping
    public String profile() {
        return profile;
    }
}
