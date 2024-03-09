package com.codemyth.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "employee")
@Data
public class EmployeeProperties {

    private String name;
    private Integer age;
}
