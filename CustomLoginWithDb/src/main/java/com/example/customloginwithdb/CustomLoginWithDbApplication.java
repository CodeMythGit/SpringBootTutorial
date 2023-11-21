package com.example.customloginwithdb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static com.example.customloginwithdb.config.SpringSecurityConfig.passwordEncoder;

@SpringBootApplication
public class CustomLoginWithDbApplication {

    public static void main(String[] args) {
        System.out.println(passwordEncoder().encode("admin"));
        SpringApplication.run(CustomLoginWithDbApplication.class, args);
    }

}
