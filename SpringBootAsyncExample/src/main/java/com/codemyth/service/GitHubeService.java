package com.codemyth.service;

import com.codemyth.model.User;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;

@Service
@AllArgsConstructor
@Slf4j
public class GitHubeService {

    private RestTemplate restTemplate;

    @Async("asycExecutor")
    public CompletableFuture<User> findUser(String userName) throws InterruptedException {
        log.info("Finding user {} on github repository", userName);
        String url = String.format("https://api.github.com/users/%s", userName);
        User result = restTemplate.getForObject(url, User.class);
        Thread.sleep(1000);

        return CompletableFuture.completedFuture(result);
    }
}
