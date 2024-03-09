package com.codemyth.controller;

import com.codemyth.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FieldInjectionController {

    @Autowired
    private HomeService homeService;

    @GetMapping("/field")
    public String message() {
        return homeService.getMessage("Field Injection");
    }
}
