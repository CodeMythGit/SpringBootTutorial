package com.example.springbootinterview.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class PathConfig extends WebMvcConfigurationSupport {
    @Override
    protected void configurePathMatch(PathMatchConfigurer configurer) {
        configurer.setPathMatcher(new AntPathMatcher(){{
            setCaseSensitive(false);
        }
        });
    }
}
