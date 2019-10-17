package com.radchenko.arhat.controller.rest.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/secure")
public class SecurityRestController {

    @GetMapping(value = "/useradmin")
    public String userAdmin() {
        return "user and admin allowed";
    }

    @GetMapping(value = "/admin")
    public String onlyAdmin() {
        return "only admin";
    }
}
