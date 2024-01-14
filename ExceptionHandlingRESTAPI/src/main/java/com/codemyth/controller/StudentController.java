package com.codemyth.controller;

import com.codemyth.model.Student;
import com.codemyth.repository.StudentRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@AllArgsConstructor
public class StudentController {

    private StudentRepository repository;

    @PostMapping("/save")
    public ResponseEntity<Object> saveStudent(@RequestBody Student student) {
        repository.save(student);
        return ResponseEntity.status(HttpStatus.CREATED).body("Student saved successfully..");
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Object> getStudentById(@PathVariable("id") Integer id) {
        Optional<Student> student = repository.findById(id);
        return ResponseEntity.status(HttpStatus.FOUND).body(student.get());
    }
}
