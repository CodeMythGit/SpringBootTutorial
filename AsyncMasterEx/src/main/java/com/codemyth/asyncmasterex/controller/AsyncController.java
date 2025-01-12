package com.codemyth.asyncmasterex.controller;

import com.codemyth.asyncmasterex.service.AsyncTaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@Slf4j
public class AsyncController {

    @Autowired
    private AsyncTaskService asyncTaskService;

    @RequestMapping("/register")
    public ResponseEntity<Object> register() throws InterruptedException {
        long t1 = System.currentTimeMillis();
        // Simulate the time required for user registration.
        Thread.sleep(20);
        // Registration is successful. Send an email.
        asyncTaskService.sendEmail();
        long t2 = System.currentTimeMillis();
        return ResponseEntity.ok("Registering a user took " + (t2-t1) + " ms");
    }
}
