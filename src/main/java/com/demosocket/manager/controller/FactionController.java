package com.demosocket.manager.controller;

import com.demosocket.manager.model.Faction;
import com.demosocket.manager.service.FactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FactionController {

    private final FactionService factionService;

    @Autowired
    public FactionController(FactionService factionService) {
        this.factionService = factionService;
    }

    @GetMapping("/faction")
    public String factionPage(Model model) {
        model.addAttribute("countAll", factionService.countAll(Faction.NAGII_UNION));
        model.addAttribute("stateInformation", factionService.findStateInformation());
        model.addAttribute("totalPopulation", factionService.findTotalPopulation());
        model.addAttribute("stationsInformation", factionService.findStationsInformation());
        model.addAttribute("influenceInformation", factionService.findInfluenceInformation());
        return "faction-page";
    }
}
