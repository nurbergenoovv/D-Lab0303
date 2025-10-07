package com.nurbergenovv.lmssystem.student;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/students")
public class StudentController {
    private final StudentService service;
    public StudentController(StudentService service) { this.service = service; }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("students", service.findAll());
        return "student/list";
    }

    @GetMapping("/add")
    public String addForm() { return "student/add"; }

    @PostMapping("/add")
    public String add(@RequestParam String name,
                      @RequestParam String surname,
                      @RequestParam int exam) {
        service.add(name, surname, exam);
        return "redirect:/students";
    }
}