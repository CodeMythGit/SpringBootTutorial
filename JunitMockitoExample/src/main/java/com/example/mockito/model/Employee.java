package com.example.mockito.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.List;

@Document(collection = "employee")
@Data
@Builder
public class Employee {
    @Id
    private String id;
    private String name;
    private Integer age;
}
