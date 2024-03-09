package com.codemyth.controller;

import com.codemyth.service.HomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RequiredArgConsController {

    private final HomeService homeService;

    @GetMapping("/req")
    public String message() {
        return homeService.getMessage("RequiredArg Injection");
    }
}
