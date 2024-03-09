package com.codmeyth.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "student")
@Data
public class PropertyConfig {

    private String name;
    private Integer age;
}
