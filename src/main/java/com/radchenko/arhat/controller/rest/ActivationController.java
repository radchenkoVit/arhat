package com.radchenko.arhat.controller.rest;

import com.radchenko.arhat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/activate")
public class ActivationController {

    @Autowired
    private UserService userService;

    @GetMapping
    @RequestMapping(value = "/{code}")
    public String activate(@PathVariable String code) {
        userService.activateUser(code);
        return "Activated";//TODO: make more better request
    }
}
