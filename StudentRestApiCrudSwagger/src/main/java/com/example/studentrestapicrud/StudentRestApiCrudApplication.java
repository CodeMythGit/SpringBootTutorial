package com.example.studentrestapicrud;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.servers.Servers;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "Student OPEN API",
                version = "1.0.0",
                description = "Student OPEN API documentation"
        ),
        servers = @Server(
                url = "http://localhost:8081",
                description = "Student OPEN API url"
        )
)
public class StudentRestApiCrudApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudentRestApiCrudApplication.class, args);
    }

}
