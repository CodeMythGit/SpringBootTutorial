package com.example.mongo.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection ="addresses")
public class Address {
    @Id
    private String id;
    private String city;
    private String pincode;
}
