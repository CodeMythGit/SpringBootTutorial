package com.codemyth.model;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class Student {

    @NotBlank(message = "Student name cannot be empty")
    @Size(min=5, max=20,message = "Name length must be between 5 and 20 characters")
    private String name;

    @Email(message = "Student email must be in valid format")
    private String email;

    @Min(value=5,message = "Age must be between 10 and 20")
    @Max(value=20,message = "Age must be between 10 and 20")
    private Integer age;
}
