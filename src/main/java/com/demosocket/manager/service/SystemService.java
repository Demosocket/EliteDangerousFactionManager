package com.demosocket.manager.service;

import com.demosocket.manager.model.System;

import java.util.List;

public interface SystemService {

    List<System> findAll();
    void saveSystem(System system);
    System findById(Long id);
    void deleteById(Long id);
}
