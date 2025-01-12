package com.codemyth.asyncmasterex.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

@Configuration
//@EnableAsync
public class AsyncConfiguration {

//    @Bean(name = "myAsyncPoolTaskExecutor")
    public ThreadPoolTaskExecutor executor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        // Core thread count.
        taskExecutor.setCorePoolSize(3);
        // The maximum number of threads maintained in the thread pool. Only when the buffer queue is full will threads exceeding the core thread count be requested.
        taskExecutor.setMaxPoolSize(5);
        // Cache queue.
        taskExecutor.setQueueCapacity(10);
        // Allowed idle time. Threads other than core threads will be destroyed after the idle time arrives.
        taskExecutor.setKeepAliveSeconds(15);
        // Thread name prefix for asynchronous methods.
        taskExecutor.setThreadNamePrefix("async-");

        taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        taskExecutor.initialize();
        return taskExecutor;
    }
}
