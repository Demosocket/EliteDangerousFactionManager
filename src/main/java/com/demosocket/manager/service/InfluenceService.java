package com.demosocket.manager.service;

import com.demosocket.manager.dto.InfluenceDto;
import com.demosocket.manager.dto.InfluenceFormDto;

import java.util.List;

public interface InfluenceService {

    List<InfluenceDto> findInfluenceDtoLastDay();

    void saveInfluence(InfluenceFormDto influenceFormDto);
}
