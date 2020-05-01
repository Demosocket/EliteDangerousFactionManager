package com.demosocket.manager.service;

import com.demosocket.manager.model.System;
import com.demosocket.manager.repository.SystemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SystemServiceImpl implements SystemService {

    private final SystemRepository systemRepository;

    @Autowired
    public SystemServiceImpl(SystemRepository systemRepository) {
        this.systemRepository = systemRepository;
    }

    public List<System> findAll() {
        return systemRepository.findAll(Sort.by(Sort.Direction.ASC, "systemNum"));
    }

    public void saveSystem(System sys) {
        // get by id
        // merge
        // save
        systemRepository.save(sys);
    }

    public System findById(Long id) {
        return systemRepository.findById(id).orElse(null);
    }

    public void deleteById(Long id) {
        systemRepository.deleteById(id);
    }
}
