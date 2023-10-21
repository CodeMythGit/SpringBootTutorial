package com.example.studentrestapicrud.service;

import com.example.studentrestapicrud.model.Student;
import com.example.studentrestapicrud.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class StudentService {

    private StudentRepository studentRepository;

    public void saveStudent(Student student) {
        studentRepository.save(student);
    }

    public Optional<List<Student>> getAllStudent() {
        return Optional.of(studentRepository.findAll());
    }

    public Optional<Student> findById(Integer id) {
        return studentRepository.findById(id);
    }

    public Optional<Student> findByCity(String city) {
        return studentRepository.findByCity(city);
    }

    public Optional<List<Student>> findByAgeLessThan(Integer age) {
        return studentRepository.findByAgeLessThan(age);
    }
}
