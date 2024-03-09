package com.codemyth.controller;

import com.codemyth.service.HomeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConstructorInjectionController {
    private final HomeService homeService;

    public ConstructorInjectionController(HomeService homeService) {
        this.homeService = homeService;
    }

    @GetMapping("/cons")
    public String message() {
        return homeService.getMessage("Constructor Injection");
    }
}
