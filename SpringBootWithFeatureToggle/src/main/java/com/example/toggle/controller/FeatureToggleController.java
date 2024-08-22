package com.example.toggle.controller;

import io.getunleash.Unleash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeatureToggleController {

    @Autowired
    private Unleash unleash;

    @GetMapping("/feature-status")
    public String checkFeature() {

        if (unleash.isEnabled("endpointFlag")) {
            return "STATUS: Endpoint flag is enabled...";
        } else {
            return "STATUS: Endpoint flag is disabled...";
        }
    }
}
