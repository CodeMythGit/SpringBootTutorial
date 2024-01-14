package com.codemyth.controller;

import com.codemyth.model.Student;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {

    @PostMapping("/save")
    public ResponseEntity<Object> saveStudent(@RequestBody @Valid Student student) {
        return ResponseEntity.status(HttpStatus.OK).body("Student saved successfully");
    }
}
