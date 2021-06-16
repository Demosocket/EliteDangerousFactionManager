package com.demosocket.manager.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum State {
    WAR("War"),
    EXPECTATION_OF_WAR("Expectation Of War"),
    ELECTIONS("Elections"),
    EXPECTATION_OF_ELECTIONS("Expectation Of Elections"),
    EXPANSION("Expansion"),
    EXPECTATION_OF_EXPANSION("Expectation Of Expansion"),
    NONE("None"),
    NO_CONTROL("No Control");

    @Getter
    public String title;

    public boolean equal(State state) {
        return this.name().equals(state.name());
    }
}
