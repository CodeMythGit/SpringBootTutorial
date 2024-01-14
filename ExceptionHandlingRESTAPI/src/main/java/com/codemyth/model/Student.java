package com.codemyth.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @NotBlank(message = "Student name cannot be empty")
    @Size(min = 3, max = 20, message = "Student name must be between 3 and 20 characters")
    private String name;

    @Email(message = "Student email is not valid")
    @NotBlank(message = "Student email cannot be empty")
    private String email;

    @Min(value = 18, message = "Student age must be between 18 and 30")
    @Max(value = 30, message = "Student age must be between 18 and 30")
    private Integer age;
}
