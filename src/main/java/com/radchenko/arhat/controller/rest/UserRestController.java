package com.radchenko.arhat.controller.rest;

import com.radchenko.arhat.service.UserService;
import com.radchenko.arhat.web.contoller.user.model.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/users")
public class UserRestController {
    private UserService userService;

    @Autowired
    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> findAll() {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/find")
    public ResponseEntity<UserDto> findByEmail(@RequestParam String email) {
        UserDto user = userService.findByEmailContains(email);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}