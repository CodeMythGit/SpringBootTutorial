package com.codemyth;

import com.codemyth.model.User;
import com.codemyth.service.GitHubeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.CompletableFuture;

@SpringBootApplication
@EnableAsync
@Slf4j
@AllArgsConstructor
public class SpringBootAsyncExampleApplication implements CommandLineRunner {

    private GitHubeService gitHubeService;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootAsyncExampleApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("Starting Spring Asychronous Application");
        CompletableFuture<User> user1 = gitHubeService.findUser("CodeMythGit");
        CompletableFuture<User> user2 = gitHubeService.findUser("java-techie-jt");
        
        CompletableFuture.allOf(user1, user2).join();
        log.info("user1 ->>>> {}", user1.get());
        log.info("user2 ->>>> {}", user2.get());
    }
}
