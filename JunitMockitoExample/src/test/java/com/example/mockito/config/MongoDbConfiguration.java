package com.example.mockito.config;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.testcontainers.containers.MongoDBContainer;

public class MongoDbConfiguration {

    static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:latest");

    @BeforeAll
    public static void setUp() {
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
            String mongoUri = mongoDBContainer.getReplicaSetUrl();
            return new MongoTemplate(new SimpleMongoClientDatabaseFactory(mongoUri));
        }
    }
}
