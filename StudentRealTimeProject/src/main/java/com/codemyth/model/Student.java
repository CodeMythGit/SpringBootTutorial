package com.codemyth.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotBlank(message = "First Name should not be empty")
    @Size(min = 2, max = 20, message = "First name length should be between 2 and 20 characters")
    private String firstName;

    @NotBlank(message = "Last Name should not be empty")
    @Size(min = 2, max = 20, message = "Last name length should be between 2 and 20 characters")
    private String lastName;

    @NotNull(message = "Age should not be null")
    @Min(value = 10, message = "Age must be greater than or equal to 10")
    @Max(value = 40, message = "Age must be less than or equal to 40")
    private Integer age;

    @NotBlank(message = "Email Address should not be empty")
    @Email(message = "Email format is not valid")
    private String emailAddress;

    private boolean active;
}
