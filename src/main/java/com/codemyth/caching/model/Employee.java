package com.codemyth.caching.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "employee")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private Double salary;
}
