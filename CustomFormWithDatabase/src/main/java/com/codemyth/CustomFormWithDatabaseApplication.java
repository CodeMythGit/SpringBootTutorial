package com.codemyth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class CustomFormWithDatabaseApplication {


    public static void main(String[] args) {

        SpringApplication.run(CustomFormWithDatabaseApplication.class, args);
    }

}
