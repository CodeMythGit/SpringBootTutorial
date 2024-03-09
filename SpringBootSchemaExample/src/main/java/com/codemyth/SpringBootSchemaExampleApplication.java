package com.codemyth;

import com.codemyth.config.CodeMythProperties;
import com.codemyth.config.PropertiesConfiguration;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@AllArgsConstructor
@Slf4j
public class SpringBootSchemaExampleApplication implements CommandLineRunner {
    private CodeMythProperties properties;

    private PropertiesConfiguration configuration;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootSchemaExampleApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("Loaded Properties file {}", properties.toString());
        log.info("Loaded configuration file {}",configuration.toString());
    }
}
