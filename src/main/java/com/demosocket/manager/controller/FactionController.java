package com.demosocket.manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FactionController {

    @GetMapping("/faction")
    public String factionPage() {
        return "faction-page";
    }
}
