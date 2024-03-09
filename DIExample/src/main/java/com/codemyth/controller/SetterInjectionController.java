package com.codemyth.controller;

import com.codemyth.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SetterInjectionController {

    private HomeService homeService;

    @Autowired
    public void setHomeService(HomeService homeService) {
        this.homeService = homeService;
    }

    @GetMapping("/setter")
    public String message() {
        return homeService.getMessage("Setter Injection");
    }
}
