package com.example.toggle;

import io.getunleash.DefaultUnleash;
import io.getunleash.Unleash;
import io.getunleash.util.UnleashConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringBootWithFeatureToggleApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootWithFeatureToggleApplication.class, args);
    }

    @Bean
    public Unleash configure() {
        UnleashConfig config = UnleashConfig.builder()
                .appName("feature-toggle-app")
                .instanceId("feature-toggle-app")
                .unleashAPI("http://localhost:4242/api")
                .apiKey("*:development.aaf974c0e039b4459a0e25aa2177b1db68f720a55c3fd914f0db9e12")
                .synchronousFetchOnInitialisation(true)
                .build();

        return new DefaultUnleash(config);
    }


}
