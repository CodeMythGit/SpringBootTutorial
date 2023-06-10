package com.example.springbootexample.controller;

import com.example.springbootexample.model.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;


@Controller
public class StudentController {


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

        System.out.println(student.getFirstName() + " " + student.getLastName() + " " + student.getEmail());
        return "success";
    }
}