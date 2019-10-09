package com.radchenko.arhat.controller.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping(value = {"/", "/index"})
    public String main() {
        return "index";
    }

    @GetMapping(value = "/register")
    public String register() {
        return "register";
    }
}
