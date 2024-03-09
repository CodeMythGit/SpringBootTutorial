package com.codemyth.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@Configuration
@PropertySources({
        @PropertySource(value = "classpath:codemyth.properties"),
        @PropertySource(value = "classpath:application.properties")
}
)
@Data
public class CodeMythProperties {

    @Value("${channel}")
    private String channel;
    @Value("${name:abcd}")
    private String name;
}
