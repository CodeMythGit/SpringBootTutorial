package com.example.springbootinterview.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotBlank(message = "firstName can not be blank")
    private String firstName;

    @Size(min=3,message = "lastName should be greater than 3 characters")
    private String lastName;

    @Min(value = 10,message = "age must be between 10 and 20")
    @Max(value = 20,message = "age must be between 10 and 20")
    private Integer age;
}
