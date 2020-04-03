package com.demosocket.manager.controller;

import com.demosocket.manager.model.System;
import com.demosocket.manager.service.SystemServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class SystemController {

    private SystemServiceImpl systemService;

    @Autowired
    public SystemController(SystemServiceImpl systemService) {
        this.systemService = systemService;
    }

    @GetMapping("/systems")
    public String findAll(Model model){
        List<System> systems = systemService.findAll();
        model.addAttribute("systems", systems);
        return "system-list";
    }

    @GetMapping("/system-add")
    public String createSystemForm(System system) {
        return "system-add";
    }

    @PostMapping("/system-add")
    public String createSystem(System system) {
        systemService.saveSystem(system);
        return "redirect:/systems";
    }

    @GetMapping("system-delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id){
        systemService.deleteById(id);
        return "redirect:/systems";
    }

    @GetMapping("/system-edit/{id}")
    public String updateSystemForm(@PathVariable("id") Integer id, Model model){
        System system = systemService.findById(id);
        model.addAttribute("system", system);
        return "system-edit";
    }

    @PostMapping("/system-edit")
    public String updateSystem(System system){
        systemService.saveSystem(system);
        return "redirect:/systems";
    }

}
