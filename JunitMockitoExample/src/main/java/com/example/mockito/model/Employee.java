package com.example.mockito.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
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

    @Min(value = 18, message = "Age must be between 18 and 60")
    @Max(value = 60, message = "Age must be between 18 and 60")
    private Integer age;
}
