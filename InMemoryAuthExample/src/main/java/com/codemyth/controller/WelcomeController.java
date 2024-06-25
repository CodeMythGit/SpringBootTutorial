package com.codemyth.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

    @GetMapping("/get")
    public String get(Authentication authentication) {
        return "Welcome " + authentication.getName() + " to spring in-memory authentication";
    }
}
