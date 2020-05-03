package com.demosocket.manager.controller;

import com.demosocket.manager.service.InfluenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/influence")
public class InfluenceController {

    private final InfluenceService influenceService;

    @Autowired
    public InfluenceController(InfluenceService influenceService) {
        this.influenceService = influenceService;
    }

    @GetMapping("/all")
    public String influencePage(Model model) {
        model.addAttribute("influences", influenceService.findAll());
        return "influence";
    }
}
