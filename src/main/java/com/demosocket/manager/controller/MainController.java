package com.demosocket.manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String frontPage() {
        return "home";
    }

    @GetMapping("/information")
    public String informationPage() {
        return "information-page";
    }
}
