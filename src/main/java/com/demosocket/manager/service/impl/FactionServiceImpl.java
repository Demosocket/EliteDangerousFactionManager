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
        StationsInformationDto stationsInformationDto = new StationsInformationDto();

        for (System sys : systemRepository.findAll()) {
            if (sys.getOrbitLargeControl() != null) {
                stationsInformationDto.addOrbitLargeControl(sys.getOrbitLargeControl());
            }
            if (sys.getOrbitLarge() != null) {
                stationsInformationDto.addOrbitLarge(sys.getOrbitLarge());
            }
            if (sys.getOrbitMediumControl() != null) {
                stationsInformationDto.addOrbitMediumControl(sys.getOrbitMediumControl());
            }
            if (sys.getOrbitMedium() != null) {
                stationsInformationDto.addOrbitMedium(sys.getOrbitMedium());
            }
            if (sys.getPlanetLargeControl() != null) {
                stationsInformationDto.addPlanetLargeControl(sys.getPlanetLargeControl());
            }
            if (sys.getPlanetLarge() != null) {
                stationsInformationDto.addPlanetLarge(sys.getPlanetLarge());
            }
            if (sys.getPlanetBaseControl() != null) {
                stationsInformationDto.addPlanetBaseControl(sys.getPlanetBaseControl());
            }
            if (sys.getPlanetBase() != null) {
                stationsInformationDto.addPlanetBase(sys.getPlanetBase());
            }
        }

        return stationsInformationDto;
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
