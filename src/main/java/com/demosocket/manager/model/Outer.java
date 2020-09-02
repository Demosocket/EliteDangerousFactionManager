package com.demosocket.manager.model;

import lombok.Data;

import java.util.Collection;

@Data
public class Outer {

    private Collection<Inner> classes;

    private String lastName;
}
