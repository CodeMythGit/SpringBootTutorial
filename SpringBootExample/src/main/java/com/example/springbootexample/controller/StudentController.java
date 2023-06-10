package com.example.springbootexample.controller;

import com.example.springbootexample.model.Student;
import com.example.springbootexample.repository.StudentRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


@Controller
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("student", new Student());
        return "StudentRegistration";
    }

    @PostMapping("/register")
    public String registerStudent(@Valid @ModelAttribute Student student, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "StudentRegistration";
        }

        studentRepository.save(student);
        List<Student> studentList = studentRepository.findAll();
        model.addAttribute("studentList", studentList);
        return "success";
    }

    @GetMapping("/viewStudent")
    public String getStudent(Model model) {
        model.addAttribute("studentList", studentRepository.findAll());
        return "success";
    }
}