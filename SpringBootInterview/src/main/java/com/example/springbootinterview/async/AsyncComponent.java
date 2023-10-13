package com.example.springbootinterview.async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AsyncComponent {

    @Async("threadPoolTaskExecutor")
    public void asyncMethodWithConfiguredExecutor() {
       log.info("Execute method asynchronously with configured executor {}", Thread.currentThread().getName());
    }
}
