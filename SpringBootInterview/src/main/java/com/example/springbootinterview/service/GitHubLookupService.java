package com.example.springbootinterview.service;

import com.example.springbootinterview.model.User;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;

@Service
@Slf4j

public class GitHubLookupService {

    @Autowired
    @Lazy
    private RestTemplate restTemplate;

    @Async("threadPoolTaskExecutor")
    public CompletableFuture<User> findUser(String user) throws InterruptedException {
        log.info("Looking up " + user);
        String url = String.format("https://api.github.com/users/%s", user);
        User results = restTemplate.getForObject(url, User.class);
        // Artificial delay of 1s for testing purposes
        Thread.sleep(1000l);
        return CompletableFuture.completedFuture(results);
    }
}
