package com.demosocket.manager.controller;

import com.demosocket.manager.dto.InfluenceDto;
import com.demosocket.manager.model.Influence;
import com.demosocket.manager.service.InfluenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.List;

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
        Date lastDate = influenceService.findFirstByOrderByDayDesc().getDay();
        model.addAttribute("lastDate", lastDate);
        model.addAttribute("influences", influenceService.findLastInfluence());
        model.addAttribute("dates", influenceService.findMyQuery());
        return "influence";
    }
}
