package com.demosocket.manager.controller;

import com.demosocket.manager.dto.TaskDto;
import com.demosocket.manager.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/")
    public String mainPage(Model model) {
        model.addAttribute("task", taskService.findLast());
        return "home";
    }

    @GetMapping("/task/edit/{id}")
    public String updateTaskForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("taskDto", taskService.findById(id));
        return "task-edit";
    }

    @PostMapping("/task/edit")
    public String updateTask(TaskDto taskDto) {
        taskService.editTask(taskDto);
        return "redirect:/";
    }

    @GetMapping("/information")
    public String informationPage() {
        return "information-page";
    }
}
