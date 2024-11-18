package com.codemyth.controller;

import com.codemyth.exception.EmployeeNotFoundException;
import com.codemyth.model.Employee;
import com.codemyth.repository.EmployeeRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class EmployeeController {

    private EmployeeRepository repository;

    @PostMapping("/save")
    public ResponseEntity<Object> saveEmployee(@RequestBody @Valid Employee employee) {
        repository.save(employee);
        return ResponseEntity.status(HttpStatus.CREATED).body("Employee saved successfully");
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Object> getEmployeeById(@PathVariable("id") Integer id) {
        Employee emp = repository.findById(id).orElseThrow(
                () -> new EmployeeNotFoundException("Employee not found for id " + id));
        return ResponseEntity.status(HttpStatus.FOUND).body(emp);
    }
}
