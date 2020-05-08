package com.demosocket.manager.service;

import com.demosocket.manager.dto.SystemDto;

import java.util.List;

public interface SystemService {

    List<SystemDto> findAll();
    SystemDto findById(Long id);
    void editSystem(SystemDto systemDto);
    void saveSystem(SystemDto systemDto);
    void deleteById(Long id);
}
