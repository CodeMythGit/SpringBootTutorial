package com.codmeyth;

import com.codmeyth.config.CodeMythProperties;
import com.codmeyth.config.PropertyConfig;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@AllArgsConstructor
@Slf4j
public class SpringPropertyExampleApplication implements CommandLineRunner {

    private CodeMythProperties properties;
    private PropertyConfig propertyConfig;
    public static void main(String[] args) {
        SpringApplication.run(SpringPropertyExampleApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("CodeMythProperties value are {}", properties.toString());
        log.info("propertyConfig value are {}", propertyConfig.toString());
    }
}
