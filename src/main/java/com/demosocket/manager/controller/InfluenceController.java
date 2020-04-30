package com.demosocket.manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InfluenceController {

    @GetMapping("/influence")
    public String influencePage() {
        return "influence";
    }
}
