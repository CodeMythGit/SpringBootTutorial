package com.example.webfluxapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("emp")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Employee {

    @Id
    private Integer id;
    private String name;
    private String city;
    private Integer age;

    public Employee(String name, String city, Integer age) {
        this.name = name;
        this.city = city;
        this.age = age;
    }
}
