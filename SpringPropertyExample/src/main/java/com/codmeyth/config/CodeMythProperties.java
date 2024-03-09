package com.codmeyth.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = "classpath:codemyth.properties")
@PropertySource(value = "classpath:application.properties")
@Data
public class CodeMythProperties {

    @Value("${name}")
    private String name;

    @Value("${city:Mumbai}")
    private String city;
}
