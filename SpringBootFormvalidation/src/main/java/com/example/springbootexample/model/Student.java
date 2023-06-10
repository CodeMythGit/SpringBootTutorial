package com.example.springbootexample.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotBlank(message = "Student first name can't be blank'")
    private String firstName;

    @NotBlank(message = "Student last name can't be blank'")
    private String lastName;

    @NotBlank(message = "Student email name can't be blank'")
    @Email(message = "Invalid email address format")
    private String email;

    @Size(min = 4, max = 8, message = "Password length must be between 4 and 8 characters")
    private String password;
}
