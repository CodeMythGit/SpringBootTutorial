package com.codemyth.asyncmasterex.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AsyncTaskService {

    @Async("myAsyncPoolTaskExecutor")
    public void sendEmail() throws InterruptedException {
        long t1 = System.currentTimeMillis();
        Thread.sleep(2000);
        long t2 = System.currentTimeMillis();
        System.out.println("Sending an email took " + (t2-t1) + " ms with Thread Name " + Thread.currentThread().getName());
    }
}
