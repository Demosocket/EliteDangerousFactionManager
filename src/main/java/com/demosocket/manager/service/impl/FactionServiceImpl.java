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

        List<Influence> influenceLastDayList =
                influenceRepository.findAllByDateOrderById(influenceRepository.findTwoLastDays().get(0));

        for (Influence inf : influenceLastDayList) {
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

        return StateInformationDto.builder()
                .controlCount(controlCount)
                .noControlCount(noControlCount)
                .expectationOfWarCount(expectationOfWarCount)
                .expectationOfElectionsCount(expectationOfElectionsCount)
                .warCount(warCount)
                .electionsCount(electionsCount)
                .build();
    }

    @Override
    public Long findTotalPopulation() {
        return systemRepository.findAllByFaction(Faction.NAGII_UNION).stream().mapToLong(System::getPopulation).sum();
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

        return StationsInformationDto.builder()
                .totalOrbitLargeControl(totalOrbitLargeControl)
                .totalOrbitLarge(totalOrbitLarge)
                .totalOrbitMediumControl(totalOrbitMediumControl)
                .totalOrbitMedium(totalOrbitMedium)
                .totalPlanetLargeControl(totalPlanetLargeControl)
                .totalPlanetLarge(totalPlanetLarge)
                .totalPlanetBaseControl(totalPlanetBaseControl)
                .totalPlanetBase(totalPlanetBase)
                .build();
    }

    @Override
    public InfluenceInformationDto findInfluenceInformation() {
        List<InfluenceDto> influenceDtoList = influenceService.findInfluenceDtoLastDay();

        return InfluenceInformationDto.builder()
                .day(getDate(influenceDtoList))
                .totalChanges(getInfluenceChanges(influenceDtoList))
                .build();
    }

    private String getDate(List<InfluenceDto> influenceDtoList) {
        return influenceDtoList.get(0).getDate();
    }

    private int getInfluenceChanges(List<InfluenceDto> influenceDtoList) {
        return influenceDtoList
                .stream()
                .mapToInt(InfluenceDto::getChanges)
                .sum();
    }
}
