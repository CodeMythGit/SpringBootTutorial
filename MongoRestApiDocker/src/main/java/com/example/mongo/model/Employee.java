package com.example.mongo.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.List;

@Data
@Document(collection = "employees")
public class Employee {

    @Id
    private String id;
    private String name;
    private Integer age;
    @DocumentReference
    private List<Address> address;

}
