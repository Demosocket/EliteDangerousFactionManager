package com.demosocket.manager.controller;

import com.demosocket.manager.dto.InfluenceFormDto;
import com.demosocket.manager.service.InfluenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/influence")
public class InfluenceController {

    private final InfluenceService influenceService;

    @Autowired
    public InfluenceController(InfluenceService influenceService) {
        this.influenceService = influenceService;
    }

    @GetMapping("/last")
    public String influencePage(Model model) {
        model.addAttribute("lastDate", influenceService.findTwoLastDays().get(0));
        model.addAttribute("influences", influenceService.findInfluenceDtoLastDay());
        return "influence";
    }

    @GetMapping("/update")
    public String influenceUpdateForm(Model model) {
        model.addAttribute("form", new InfluenceFormDto(influenceService.findInfluenceDtoLastDay()));
        return "influence-update";
    }

    @PostMapping("/update")
    public String influenceUpdate(@ModelAttribute("form") InfluenceFormDto form) {
        influenceService.saveInfluence(form);
        return "redirect:/influence/last";
    }
}
