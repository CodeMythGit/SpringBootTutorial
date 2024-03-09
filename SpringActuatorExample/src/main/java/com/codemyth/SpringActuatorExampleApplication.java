package com.codemyth;

import com.codemyth.beans.Student;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringActuatorExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringActuatorExampleApplication.class, args);
    }

    @Bean
    public Student student() {
        return new Student();
    }
}
