package com.demosocket.manager.service;

import com.demosocket.manager.model.System;
import com.demosocket.manager.repository.SystemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SystemServiceImpl implements SystemService {

    private SystemRepository systemRepository;

    @Autowired
    public SystemServiceImpl(SystemRepository systemRepository) {
        this.systemRepository = systemRepository;
    }

    public List<System> findAll() {
        return systemRepository.findAll(Sort.by(Sort.Direction.ASC, "Id"));
    }

    public void saveSystem(System system) {
        systemRepository.save(system);
    }

    public System findById(Integer id) {
        return systemRepository.findById(id).orElse(null);
    }

    public void deleteById(Integer id) {
        systemRepository.deleteById(id);
    }
}
