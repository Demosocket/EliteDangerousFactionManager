package com.demosocket.manager.controller;

import com.demosocket.manager.model.System;
import com.demosocket.manager.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/systems")
public class SystemController {

    private final SystemService systemService;

    @Autowired
    public SystemController(SystemService systemService) {
        this.systemService = systemService;
    }

    @GetMapping("/all")
    public String findAll(Model model){
        List<System> systems = systemService.findAll();
        model.addAttribute("systems", systems);
        return "system-list";
    }

    @GetMapping("/add")
    public String createSystemForm(System sys) {
        return "system-add";
    }

    @PostMapping("/add")
    public String createSystem(System sys) {
        systemService.saveSystem(sys);
        return "redirect:/systems/all";
    }

    @GetMapping("/edit/{id}")
    public String updateSystemForm(@PathVariable("id") Long id, Model model){
        System sys = systemService.findById(id);
        model.addAttribute("sys", sys);
        return "system-edit";
    }

    @PostMapping("/edit")
    public String updateSystem(System sys){
        systemService.saveSystem(sys);
        return "redirect:/systems/all";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id){
        systemService.deleteById(id);
        return "redirect:/systems/all";
    }
}
