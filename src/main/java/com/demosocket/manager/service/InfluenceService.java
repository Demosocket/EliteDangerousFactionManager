package com.demosocket.manager.service;

import com.demosocket.manager.dto.InfluenceDto;
import com.demosocket.manager.dto.InfluenceFormDto;

import java.util.Date;
import java.util.List;

public interface InfluenceService {

    List<InfluenceDto> findInfluenceDtoLastDay();
    List<Date> findTwoLastDays();
    void saveInfluence(InfluenceFormDto influenceFormDto, Date date);
}
