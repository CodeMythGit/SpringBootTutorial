package com.example.springbootexample.model;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Student {

    @NotBlank(message = "Student first name can't be blank'")
    private  String firstName;

    @NotBlank(message = "Student last name can't be blank'")
    private String lastName;

    @NotBlank(message = "Student email name can't be blank'")
    @Email(message = "Invalid email address format")
    private String email;

    @Size(min = 4,max=8,message = "Password length must be between 4 and 8 characters")
    private String password;
}
