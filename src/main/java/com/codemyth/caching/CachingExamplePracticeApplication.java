package com.codemyth.caching;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class CachingExamplePracticeApplication {

    public static void main(String[] args) {
        SpringApplication.run(CachingExamplePracticeApplication.class, args);
    }

}
