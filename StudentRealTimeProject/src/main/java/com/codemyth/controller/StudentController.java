package com.codemyth.controller;

import com.codemyth.model.Student;
import com.codemyth.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/register")
    public String getStudentForm(Model model) {
        model.addAttribute("student", new Student());
        return "register";
    }

    @PostMapping("/save")
    public String saveStudentForm(@Valid @ModelAttribute("student") Student student, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("student", student);
            return "register";
        }

        studentService.saveStudent(student);
        model.addAttribute("studentList", studentService.findAllStudent());
        return "success";
    }

    @GetMapping("/viewStudent")
    public String viewStudent(Model model) {
        model.addAttribute("studentList", studentService.findAllStudent());
        return "success";
    }

    @GetMapping("/editStudent/{id}")
    public String editStudent(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("student", studentService.findStudent(id));
        return "register";
    }

    @GetMapping("/deleteStudent/{id}")
    public String deleteStudent(@PathVariable("id") Integer id, Model model) {
        studentService.deleteStudent(id);
        model.addAttribute("studentList", studentService.findAllStudent());
        return "success";
    }

}
