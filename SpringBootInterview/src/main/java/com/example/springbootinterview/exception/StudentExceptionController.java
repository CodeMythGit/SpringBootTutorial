package com.example.springbootinterview.exception;

import com.example.springbootinterview.model.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class StudentExceptionController {

    @ExceptionHandler(value = StudentNotFoundException.class)
    public ResponseEntity<Object> exception(StudentNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student not found with cause " + exception.getMessage());
    }
}
