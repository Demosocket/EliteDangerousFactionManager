package com.demosocket.manager.service;

import com.demosocket.manager.dto.InfluenceInformationDto;
import com.demosocket.manager.dto.StateInformationDto;
import com.demosocket.manager.dto.StationsInformationDto;
import com.demosocket.manager.model.Faction;

public interface FactionService {

    Integer countAll(Faction faction);

    StateInformationDto findStateInformation();

    Long findTotalPopulation();

    StationsInformationDto findStationsInformation();

    InfluenceInformationDto findInfluenceInformation();
}
