package com.radchenko.arhat.controller.rest;

import com.radchenko.arhat.service.RegistrationService;
import com.radchenko.arhat.web.contoller.user.model.RegistrationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api")
public class RegistrationController {
    @Autowired
    private RegistrationService registrationService;

    @PostMapping(value = "/auth/registration")
    public ResponseEntity registerUser(@RequestBody @Valid RegistrationRequest request) {
        registrationService.register(request);
        return new ResponseEntity(null, HttpStatus.CREATED);
    }
}
