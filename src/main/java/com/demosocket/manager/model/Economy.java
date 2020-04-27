package com.demosocket.manager.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public enum Economy {
    INDUSTRIAL("Industrial"),
    EXTRACTION("Extraction"),
    REFINERY("Refinery"),
    MILITARY("Military"),
    COLONY("Colony"),
    TERRAFORMING("Terraforming"),
    HIGH_TECH("High Tech"),
    AGRICULTURE("Agriculture"),
    PRISON("Prison"),
    SERVICE("Service"),
    TOURISM("Tourism"),
    DAMAGER("Damager"),
    REPAIR("Repair"),
    RESCUE("Rescue"),
    NONE("None");

    private String title;
}
