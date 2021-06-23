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
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import static com.demosocket.manager.model.State.*;

@Service
public class FactionServiceImpl implements FactionService {

    private final SystemRepository systemRepository;
    private final InfluenceRepository influenceRepository;
    private final InfluenceService influenceService;

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

        StateInformationDto informationDto = new StateInformationDto();

        Date lastUpdate = influenceRepository.findTwoLastDays().get(0);
        List<Influence> influenceLastUpdateList = influenceRepository.findAllByDateOrderById(lastUpdate);

        for (Influence inf : influenceLastUpdateList) {
            State state = inf.getState();

            if (EXPANSION.equal(state) || EXPECTATION_OF_EXPANSION.equal(state) || NONE.equal(state)) {
                informationDto.incrementControlCount();
            } else if (NO_CONTROL.equal(state)) {
                informationDto.incrementNoControlCount();
            } else if (EXPECTATION_OF_WAR.equal(state)) {
                informationDto.incrementExpectationOfWarCount();
            } else if (EXPECTATION_OF_ELECTIONS.equal(state)) {
                informationDto.incrementExpectationOfElectionsCount();
            } else if (WAR.equals(state)) {
                informationDto.incrementWarCount();
            } else if (ELECTIONS.equal(state)) {
                informationDto.incrementElectionsCount();
            }
        }

        return informationDto;
    }

    @Override
    public Long findTotalPopulation() {
        return systemRepository.findAllByFaction(Faction.NAGII_UNION)
                .stream()
                .mapToLong(System::getPopulation)
                .sum();
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
