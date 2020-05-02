package com.demosocket.manager.service;

import com.demosocket.manager.dto.SystemDto;
import com.demosocket.manager.model.Economy;
import com.demosocket.manager.model.Faction;
import com.demosocket.manager.model.System;
import com.demosocket.manager.repository.SystemRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SystemServiceImpl implements SystemService {

    private final SystemRepository systemRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public SystemServiceImpl(SystemRepository systemRepository, ModelMapper modelMapper) {
        this.systemRepository = systemRepository;
        this.modelMapper = modelMapper;
    }

    public List<SystemDto> findAll() {
        List<System> systemList = systemRepository.findAll(Sort.by(Sort.Direction.ASC, "systemNum"));
        List<SystemDto> systemDtoList = new ArrayList<>();
        for (System system : systemList) {
            SystemDto systemDto = modelMapper.map(system, SystemDto.class);
            systemDto.setPrimaryEconomy(system.getPrimaryEconomy().getTitle());
            systemDto.setSecondaryEconomy(system.getSecondaryEconomy().getTitle());
            systemDtoList.add(systemDto);
        }
        return systemDtoList;
    }

    public SystemDto findById(Long id) {
        System systemFromDb = systemRepository.findById(id).orElse(null);
        if (systemFromDb == null) {
            return null;
        } else {
            SystemDto systemDto = modelMapper.map(systemFromDb, SystemDto.class);
            systemDto.setPrimaryEconomy(systemFromDb.getPrimaryEconomy().getTitle());
            systemDto.setSecondaryEconomy(systemFromDb.getSecondaryEconomy().getTitle());
            return systemDto;
        }
    }

    public void saveSystem(SystemDto systemDto) {
        System system = modelMapper.map(systemDto, System.class);
        system.setFaction(Faction.NAGII_UNION);
        system.setPrimaryEconomy(Economy.valueOf(
                systemDto.getPrimaryEconomy().toUpperCase().replaceAll("\\s+", "_")
        ));
        system.setSecondaryEconomy(Economy.valueOf(
                systemDto.getSecondaryEconomy().toUpperCase().replaceAll("\\s+", "_")
        ));
        systemRepository.save(system);
    }

    public void deleteById(Long id) {
        systemRepository.deleteById(id);
    }
}
