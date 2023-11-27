package com.codemyth.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

    @GetMapping("/user")
    public String authenticate(Authentication authentication) {
        return "Hello " + authentication.getName() + " to Spring Security Tutorial!!!";
    }
}
