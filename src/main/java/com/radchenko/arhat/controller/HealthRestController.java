package com.radchenko.arhat.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/health")
public class HealthRestController {

    @GetMapping
    public String health() {
        return "Up and running";
    }

    @GetMapping(value = "/private")
    public String privateContent() {
        return "Private Content";
    }
}
