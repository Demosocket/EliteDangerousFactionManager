package com.demosocket.manager.service.impl;

import com.demosocket.manager.dto.InfluenceDto;
import com.demosocket.manager.dto.InfluenceInformationDto;
import com.demosocket.manager.dto.StateInformationDto;
import com.demosocket.manager.dto.StationsInformationDto;
import com.demosocket.manager.model.Faction;
import com.demosocket.manager.model.Influence;
import com.demosocket.manager.model.State;
import com.demosocket.manager.model.System;
import com.demosocket.manager.repository.InfluenceRepository;
import com.demosocket.manager.repository.SystemRepository;
import com.demosocket.manager.service.FactionService;
import com.demosocket.manager.service.InfluenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FactionServiceImpl implements FactionService {

    private final SystemRepository systemRepository;
    private final InfluenceRepository influenceRepository;
    private final InfluenceService influenceService;

    @Autowired
    public FactionServiceImpl(SystemRepository systemRepository,
                              InfluenceRepository influenceRepository,
                              InfluenceService influenceService) {
        this.systemRepository = systemRepository;
        this.influenceRepository = influenceRepository;
        this.influenceService = influenceService;
    }

    @Override
    public Integer countAll(Faction faction) {
        return systemRepository.countAllByFaction(faction);
    }

    @Override
    public StateInformationDto findStateInformation() {
        Integer controlCount = 0;
        Integer noControlCount = 0;
        Integer expectationOfWarCount = 0;
        Integer expectationOfElectionsCount = 0;
        Integer warCount = 0;
        Integer electionsCount = 0;
        for (Influence inf :
                influenceRepository.findAllByDayOrderById(influenceRepository.findTwoLastDays().get(0))) {
            if (inf.getState().equals(State.EXPANSION)
                    || inf.getState().equals(State.EXPECTATION_OF_EXPANSION)
                    || inf.getState().equals(State.NONE)) {
                controlCount++;
            } else if (inf.getState().equals(State.NO_CONTROL)) {
                noControlCount++;
            } else if (inf.getState().equals(State.EXPECTATION_OF_WAR)) {
                expectationOfWarCount++;
            } else if (inf.getState().equals(State.EXPECTATION_OF_ELECTIONS)) {
                expectationOfElectionsCount++;
            } else if (inf.getState().equals(State.WAR)) {
                warCount++;
            } else {
                electionsCount++;
            }
        }
        return new StateInformationDto(
                controlCount,
                noControlCount,
                expectationOfWarCount,
                expectationOfElectionsCount,
                warCount,
                electionsCount);
    }

    @Override
    public Long findTotalPopulation() {
        long totalPopulation = 0;
        for (Influence inf :
                influenceRepository.findAllByDayOrderById(influenceRepository.findTwoLastDays().get(0))) {
            totalPopulation += inf.getSystem().getPopulation();
        }
        return totalPopulation;
    }

    @Override
    public StationsInformationDto findStationsInformation() {
        Integer totalOrbitLargeControl = 0;
        Integer totalOrbitLarge = 0;
        Integer totalOrbitMediumControl = 0;
        Integer totalOrbitMedium = 0;
        Integer totalPlanetLargeControl = 0;
        Integer totalPlanetLarge = 0;
        Integer totalPlanetBaseControl = 0;
        Integer totalPlanetBase = 0;
        for (System sys : systemRepository.findAll()) {
            if (sys.getOrbitLargeControl() != null) {
                totalOrbitLargeControl += sys.getOrbitLargeControl();
            }
            if (sys.getOrbitLarge() != null) {
                totalOrbitLarge += sys.getOrbitLarge();
            }
            if (sys.getOrbitMediumControl() != null) {
                totalOrbitMediumControl += sys.getOrbitMediumControl();
            }
            if (sys.getOrbitMedium() != null) {
                totalOrbitMedium += sys.getOrbitMedium();
            }
            if (sys.getPlanetLargeControl() != null) {
                totalPlanetLargeControl += sys.getPlanetLargeControl();
            }
            if (sys.getPlanetLarge() != null) {
                totalPlanetLarge += sys.getPlanetLarge();
            }
            if (sys.getPlanetBaseControl() != null) {
                totalPlanetBaseControl += sys.getPlanetBaseControl();
            }
            if (sys.getPlanetBase() != null) {
                totalPlanetBase += sys.getPlanetBase();
            }
        }
        return new StationsInformationDto(
                totalOrbitLargeControl,
                totalOrbitLarge,
                totalOrbitMediumControl,
                totalOrbitMedium,
                totalPlanetLargeControl,
                totalPlanetLarge,
                totalPlanetBaseControl,
                totalPlanetBase);
    }

    @Override
    public InfluenceInformationDto findInfluenceInformation() {
        List<InfluenceDto> influenceDtoList = influenceService.findInfluenceDtoLastDay();
        String day = influenceDtoList.get(0).getDay();
        Integer sum = 0;
        Integer totalChanges = 0;
        for (InfluenceDto inf : influenceDtoList) {
            totalChanges += inf.getChanges();
            sum += inf.getInfluence();
        }
        Integer averageInfluence = sum/influenceDtoList.size();
        return new InfluenceInformationDto(day, averageInfluence, totalChanges);
    }
}