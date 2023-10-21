package com.example.studentrestapicrud.controller;

import com.example.studentrestapicrud.model.Student;
import com.example.studentrestapicrud.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/restAPI")
@AllArgsConstructor
@Tag(name = "StudentController", description = "To perform operations on students")
public class StudentController {

    private StudentService studentService;

    @Operation(
            summary = "POST operations on students",
            description = "It is used to save student object in database"
    )
    @PostMapping("/addStudent")
    public ResponseEntity<Object> addStudent(@RequestBody Student student) {
        studentService.saveStudent(student);
        return ResponseEntity.status(HttpStatus.OK).body("Student added successfully");
    }

    @Operation(
            summary = "GET operations on students",
            description = "It is used to retrieve student object from database"
    )
    @GetMapping("/getStudent")
    public ResponseEntity<Object> getStudent() {
        Optional<List<Student>> studentList = studentService.getAllStudent();
        if (studentList.isPresent()) {
            return ResponseEntity.status(HttpStatus.FOUND).body(studentList.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student not found");
        }
    }
    @Operation(
            summary = "GET operations on students by using student id",
            description = "It is used to retrieve student object from database using student id"
    )
    @GetMapping("/getStudent/id/{id}")
    public ResponseEntity<Object> getStudentById(@PathVariable Integer id) {
        Optional<Student> student = studentService.findById(id);
        if (student.isPresent()) {
            return ResponseEntity.status(HttpStatus.FOUND).body(student.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student not found for id: " + id);
        }
    }

    @Operation(
            summary = "PUT operations on students by using student id",
            description = "It is used to update student object in database using student id"
    )
    @PutMapping("/updateStudent/{id}")
    public ResponseEntity<Object> updateStudent(@PathVariable Integer id, @RequestBody Student student) {
        Optional<Student> studentOptional = studentService.findById(id);
        if (studentOptional.isPresent()) {
            student.setId(id);
            studentService.saveStudent(student);
            return ResponseEntity.status(HttpStatus.OK).body("Student updated successfully for id: " + id);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Update Failed!!! Student not found for id: " + id);
        }
    }

    @Operation(
            summary = "GET operations on students by using student city",
            description = "It is used to retrieve student object from database using student city name"
    )
    @GetMapping("/getStudent/city/{city}")
    public ResponseEntity<Object> findStudentByCity(@PathVariable String city) {
        Optional<Student> studentOptional = studentService.findByCity(city);
        if (studentOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.FOUND).body(studentOptional.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student not found for city: " + city);
        }
    }

    @Operation(
            summary = "GET operations on students by using student age",
            description = "It is used to retrieve student object from database using student age name"
    )
    @GetMapping("/getStudent/age/{age}")
    public ResponseEntity<Object> findStudentByFilter(@PathVariable Integer age) {
        Optional<List<Student>> studentOptional = studentService.findByAgeLessThan(age);
        if (studentOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.FOUND).body(studentOptional.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student not found for age less than : " + age);
        }
    }
}
