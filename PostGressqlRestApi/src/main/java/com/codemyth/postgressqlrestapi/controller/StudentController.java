package com.codemyth.postgressqlrestapi.controller;

import com.codemyth.postgressqlrestapi.model.Student;
import com.codemyth.postgressqlrestapi.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @PostMapping("/saves")
    public ResponseEntity saveStudent(@RequestBody Student student){
        studentRepository.save(student);
        return ResponseEntity.status(HttpStatus.CREATED).body("Student created successfully!!!");
    }

    @GetMapping("/get")
    public ResponseEntity getAllStudents(){
        List<Student> studentList = studentRepository.findAll();
        if(studentList.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No record found!!!");
        }else{
            return ResponseEntity.status(HttpStatus.FOUND).body(studentList);
        }

    }
}
