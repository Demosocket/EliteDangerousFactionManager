package com.demosocket.manager.service.impl;

import com.demosocket.manager.dto.SystemDto;
import com.demosocket.manager.model.Economy;
import com.demosocket.manager.model.Influence;
import com.demosocket.manager.model.State;
import com.demosocket.manager.model.System;
import com.demosocket.manager.repository.InfluenceRepository;
import com.demosocket.manager.repository.SystemRepository;
import com.demosocket.manager.service.SystemService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static com.demosocket.manager.model.Faction.NAGII_UNION;

@Service
public class SystemServiceImpl implements SystemService {

    private final SystemRepository systemRepository;
    private final InfluenceRepository influenceRepository;
    private final ModelMapper modelMapper;

    public SystemServiceImpl(SystemRepository systemRepository,
                             InfluenceRepository influenceRepository,
                             ModelMapper modelMapper) {
        this.systemRepository = systemRepository;
        this.influenceRepository = influenceRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<SystemDto> findAll() {
        List<System> systemList = systemRepository.findAllByFaction(NAGII_UNION);
        systemList.sort(Comparator.comparing(System::getId));

        List<SystemDto> systemDtoList = new ArrayList<>();
        for (System system : systemList) {
            systemDtoList.add(getSystemFromDto(system));
        }

        return systemDtoList;
    }

    @Override
    public SystemDto findById(Long id) {
        System systemFromDb = systemRepository.findById(id).orElseThrow(EntityNotFoundException::new);

        return getSystemFromDto(systemFromDb);
    }

    @Override
    public void editSystem(SystemDto systemDto) {
        save(systemDto);
    }

    @Override
    public void saveSystem(SystemDto systemDto) {
        System system = save(systemDto);

        Influence influence = Influence.builder()
                .system(system)
                .date(influenceRepository.findTwoLastDays().get(0))
                .influence(0)
                .state(State.NO_CONTROL)
                .build();

        influenceRepository.save(influence);
    }

    @Override
    public void deleteById(Long id) {
        systemRepository.deleteById(id);
    }

    private SystemDto getSystemFromDto(System system) {
        SystemDto systemDto = modelMapper.map(system, SystemDto.class);
        systemDto.setPrimaryEconomy(system.getPrimaryEconomy().getTitle());
        systemDto.setSecondaryEconomy(system.getSecondaryEconomy().getTitle());

        return systemDto;
    }

    private System save(SystemDto systemDto) {
        System system = modelMapper.map(systemDto, System.class);
        system.setFaction(NAGII_UNION);
        system.setPrimaryEconomy(getEconomyFromEnum(systemDto.getPrimaryEconomy()));
        system.setSecondaryEconomy(getEconomyFromEnum(systemDto.getSecondaryEconomy()));
        systemRepository.save(system);

        return system;
    }

    private Economy getEconomyFromEnum(String economy) {
        return Economy.valueOf(economy.toUpperCase().replaceAll("\\s+", "_"));
    }
}
