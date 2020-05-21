package com.demosocket.manager.controller;

import com.demosocket.manager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    private final TaskService taskService;

    @Autowired
    public MainController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/")
    public String mainPage(Model model) {
        model.addAttribute("task", taskService.findLast());
        return "home";
    }

    @GetMapping("/information")
    public String informationPage() {
        return "information-page";
    }
}
