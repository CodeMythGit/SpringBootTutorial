package com.codemyth.service;

import com.codemyth.model.Student;
import com.codemyth.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public void saveStudent(Student student) {
        studentRepository.save(student);
    }

    public List<Student> findAllStudent() {
        return studentRepository.findAll();
    }

    public Student findStudent(Integer id) {
        return studentRepository.findById(id).get();
    }

    public void deleteStudent(Integer id) {
        studentRepository.deleteById(id);
    }
}
