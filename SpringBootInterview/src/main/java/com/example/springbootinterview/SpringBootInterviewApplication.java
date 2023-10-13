package com.example.springbootinterview;

import com.example.springbootinterview.model.User;
import com.example.springbootinterview.service.GitHubLookupService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.WebRequestHandlerInterceptorAdapter;

import java.util.concurrent.CompletableFuture;

@SpringBootApplication
@Slf4j
@AllArgsConstructor
@EnableScheduling
/*@PropertySources({
        @PropertySource("classpath:application.properties"),
        @PropertySource(value = "file:/path/to/config1.properties", ignoreResourceNotFound = true),
        @PropertySource(value = "file:/path/to/config2.properties", ignoreResourceNotFound = true),
        @PropertySource(value = "file:/path/to/config3.properties", ignoreResourceNotFound = true)
})*/
public class SpringBootInterviewApplication implements CommandLineRunner {

    private GitHubLookupService gitHubLookupService;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootInterviewApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }


    @Override
    public void run(String... args) throws Exception {
        // Start the clock
        long start = System.currentTimeMillis();

        // Kick of multiple, asynchronous lookups
        CompletableFuture<User> user1 = gitHubLookupService.findUser("CodeMythGit");
        CompletableFuture<User> user2 = gitHubLookupService.findUser("Java-Techie-jt");
        // Wait until they are all done
        CompletableFuture.allOf(user1, user2).join();

        // Print results, including elapsed time
        log.info("Elapsed time: " + (System.currentTimeMillis() - start));
        log.info("--> " + user1.get());
        log.info("--> " + user2.get());
    }
}
