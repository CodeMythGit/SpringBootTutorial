package com.example.springbootinterview.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {

    private String name;
    private String type;
    private String email;
    private String bio;
}
