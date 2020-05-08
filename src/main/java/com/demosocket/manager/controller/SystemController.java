package com.demosocket.manager.controller;

import com.demosocket.manager.dto.SystemDto;
import com.demosocket.manager.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
        model.addAttribute("systemsDb", systemService.findAll());
        return "system-list";
    }

    @GetMapping("/add")
    public String createSystemForm(SystemDto systemDto) {
        return "system-add";
    }

    @PostMapping("/add")
    public String createSystem(SystemDto systemDto) {
        systemService.saveSystem(systemDto);
        return "redirect:/systems/all";
    }

    @GetMapping("/edit/{id}")
    public String updateSystemForm(@PathVariable("id") Long id, Model model){
        model.addAttribute("systemDto", systemService.findById(id));
        return "system-edit";
    }

    @PostMapping("/edit")
    public String updateSystem(SystemDto systemDto){
        systemService.editSystem(systemDto);
        return "redirect:/systems/all";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id){
        systemService.deleteById(id);
        return "redirect:/systems/all";
    }
}
