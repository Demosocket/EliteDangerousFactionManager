package com.demosocket.manager.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum Role {
    USER("User"),
    COMRADE("Comrade"),
    COMMUNIST("Communist"),
    COMMANDER("Commander");

    @Getter
    private final String title;
}
