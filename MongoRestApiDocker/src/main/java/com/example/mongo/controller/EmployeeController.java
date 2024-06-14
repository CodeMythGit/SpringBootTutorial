package com.example.mongo.controller;

import com.example.mongo.model.Employee;
import com.example.mongo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/emp")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<Object> saveEmployee(@RequestBody Employee employee) {
        return ResponseEntity.ok(employeeService.saveEmployee(employee));
    }

    @GetMapping
    public ResponseEntity<Object> getAllEmployee() {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

}
