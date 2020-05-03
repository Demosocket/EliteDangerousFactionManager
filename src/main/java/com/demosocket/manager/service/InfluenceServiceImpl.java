package com.demosocket.manager.service;

import com.demosocket.manager.model.Influence;
import com.demosocket.manager.repository.InfluenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InfluenceServiceImpl implements InfluenceService {

    private final InfluenceRepository influenceRepository;

    @Autowired
    public InfluenceServiceImpl(InfluenceRepository influenceRepository) {
        this.influenceRepository = influenceRepository;
    }

    @Override
    public List<Influence> findAll() {
        return influenceRepository.findAll();
    }
}
