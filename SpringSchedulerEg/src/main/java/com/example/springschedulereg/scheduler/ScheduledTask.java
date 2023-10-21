package com.example.springschedulereg.scheduler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Slf4j
public class ScheduledTask {

    @Scheduled(initialDelay = 1000, fixedRate = 2000)
    public void task1() {
        log.info("Inside task1 : Current Time : {}", LocalDateTime.now());
    }

    @Scheduled(fixedRate = 2000)
    public void task2() {
        log.info("Inside task2 : Current Time : {}", LocalDateTime.now());
    }

    @Scheduled(cron = "@hourly")
    public void task3() {
        log.info("Inside task3 : Current Time : {}", LocalDateTime.now());
    }
}
