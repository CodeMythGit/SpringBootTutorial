package com.example.springbootinterview.controller;

import com.example.springbootinterview.exception.StudentNotFoundException;
import com.example.springbootinterview.model.Student;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/students")
public class StudentController {

    @PostMapping("/add")
    public ResponseEntity<Object> saveStudent(@RequestBody Student student) {
        return ResponseEntity.status(HttpStatus.OK).body("Data sent successfully");
    }

    @GetMapping("/get")
    public ResponseEntity<Object> getAllStudent() {
        return ResponseEntity.status(HttpStatus.FOUND).body("Data found successfully");
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Object> getStudent(@PathVariable String id) {
        throw new StudentNotFoundException("id" + id + " not present in the database");
    }

    @PostMapping("/validate")
    public ResponseEntity<Object> validatedStudent(@RequestBody @Valid Student student, BindingResult result) {
        if(result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result.getAllErrors());
        }
        return ResponseEntity.status(HttpStatus.OK).body("Data sent successfully");
    }

    @GetMapping("/logs")
    public ResponseEntity<Object> checkLogs() {
        log.info("This info type log message");
        log.warn("This info type log message");
        log.error("This info warn log message");
        return ResponseEntity.status(HttpStatus.OK).body("Logs printed successfully");
    }
}
