package com.codemyth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/welcome")
    public String getWelcomePage() {
        return "welcome";
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }
}
