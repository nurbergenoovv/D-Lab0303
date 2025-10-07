package com.nurbergenovv.lmssystem.task;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService service;
    public TaskController(TaskService service) { this.service = service; }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("tasks", service.findAll());
        return "task/list";
    }

    @GetMapping("/add")
    public String addForm() { return "task/add"; }

    @PostMapping("/add")
    public String add(@RequestParam String name,
                      @RequestParam String deadlineDate,
                      @RequestParam(required = false, defaultValue = "false") boolean isCompleted) {
        service.create(name, deadlineDate, isCompleted);
        return "redirect:/tasks";
    }

    @GetMapping("/{id}")
    public String view(@PathVariable Long id, Model model) {
        var task = service.findById(id).orElse(null);
        model.addAttribute("task", task);
        return "task/view";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("task", service.findById(id).orElse(null));
        return "task/edit";
    }

    @PostMapping("/{id}/edit")
    public String edit(@PathVariable Long id,
                       @RequestParam String name,
                       @RequestParam String deadlineDate,
                       @RequestParam(required = false, defaultValue = "false") boolean isCompleted) {
        service.update(id, name, deadlineDate, isCompleted);
        return "redirect:/tasks";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/tasks";
    }
}