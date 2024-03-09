package com.codemyth.service;

import org.springframework.stereotype.Service;

@Service
public class HomeService {

    public String getMessage(String message) {
        return "Hello from " + message;
    }
}
