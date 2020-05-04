package com.demosocket.manager.service;

import com.demosocket.manager.dto.InfluenceDto;
import com.demosocket.manager.model.Influence;

import java.util.Date;
import java.util.List;

public interface InfluenceService {
//    List<InfluenceDto> findAll();
//
    Influence findFirstByOrderByDayDesc();
//
//    List<InfluenceDto> findByDay(Date date);

    List<InfluenceDto> findLastInfluence();
    List<Date> findMyQuery();
}
