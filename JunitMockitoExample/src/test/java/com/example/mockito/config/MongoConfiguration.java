package com.example.mockito.config;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.testcontainers.containers.MongoDBContainer;

public class MongoConfiguration {

    static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:latest");

    @BeforeAll
    public static void setup() {
        mongoDBContainer.start();
    }

    @AfterAll
    public static void tearDown() {
        mongoDBContainer.stop();
    }

    @Configuration
    public static class MongoConfig {
        @Bean
        public MongoTemplate mongoTemplate() {
            String mongoUrl = mongoDBContainer.getReplicaSetUrl();
            return new MongoTemplate(new SimpleMongoClientDatabaseFactory(mongoUrl));
        }
    }

}
