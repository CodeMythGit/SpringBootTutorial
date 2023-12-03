package com.example.webfluxapp.controller;

import com.example.webfluxapp.model.Employee;
import com.example.webfluxapp.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/employee")
@AllArgsConstructor
public class EmployeeController {

    private EmployeeRepository repository;

    @PostMapping("/create")
    public Mono<Employee> create(@RequestBody Employee employee) {
        return repository.save(employee);
    }

    @GetMapping("/get")
    public Flux<Employee> get() {
        return repository.findAll();
    }

    @PutMapping("/update/{id}")
    public Mono<Employee> update(@RequestBody Employee employee, @PathVariable("id") Integer id) {
        Employee emp = Employee.builder()
                .id(id)
                .name(employee.getName())
                .age(employee.getAge())
                .city(employee.getCity())
                .build();
        return repository.save(emp);
    }

    @DeleteMapping("/delete/{id}")
    public Mono<Void> delete(@PathVariable("id") Integer id) {
        return repository.deleteById(id);
    }
}
