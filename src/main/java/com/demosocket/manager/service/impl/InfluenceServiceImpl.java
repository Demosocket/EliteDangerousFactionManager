package com.demosocket.manager.service.impl;

import com.demosocket.manager.dto.InfluenceDto;
import com.demosocket.manager.dto.InfluenceFormDto;
import com.demosocket.manager.model.Influence;
import com.demosocket.manager.model.State;
import com.demosocket.manager.model.System;
import com.demosocket.manager.repository.InfluenceRepository;
import com.demosocket.manager.repository.SystemRepository;
import com.demosocket.manager.service.InfluenceService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.*;

@Service
public class InfluenceServiceImpl implements InfluenceService {

    private final InfluenceRepository influenceRepository;
    private final SystemRepository systemRepository;
    private final ModelMapper modelMapper;

    public InfluenceServiceImpl(InfluenceRepository influenceRepository,
                                SystemRepository systemRepository,
                                ModelMapper modelMapper) {
        this.influenceRepository = influenceRepository;
        this.systemRepository = systemRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<InfluenceDto> findInfluenceDtoLastDay() {
        // Find two last days in db
        List<Date> lastTwoDays = influenceRepository.findTwoLastDays();
        // Make query to find 2 lists of influences
        List<Influence> influenceLastDayList = influenceRepository.findAllByDateOrderById(lastTwoDays.get(0));
        List<Influence> influenceDayBeforeLastList = influenceRepository.findAllByDateOrderById(lastTwoDays.get(1));

        // InfluenceDto for Nagii - main system
        InfluenceDto influenceDtoNagii = getInfluenceDtoNagii(influenceLastDayList, influenceDayBeforeLastList);

        List<InfluenceDto> influenceDtoResultList = new ArrayList<>();
        if (influenceLastDayList.size() == influenceDayBeforeLastList.size()) {
            calculateInfluence(influenceLastDayList,
                    influenceDayBeforeLastList,
                    influenceDtoResultList,
                    influenceLastDayList.size());
        } else if (influenceLastDayList.size() > influenceDayBeforeLastList.size()) {
            calculateInfluenceWithNewSystem(influenceLastDayList,
                    influenceDayBeforeLastList,
                    influenceDtoResultList);
        }
        // TODO check when we lost the system

        // sort the resulting list for easy presentation
        influenceDtoResultList.sort(Comparator.comparing(InfluenceDto::getSystemName));
        // and set Nagii on first position
        influenceDtoResultList.add(0, influenceDtoNagii);

        return influenceDtoResultList;
    }

    @Override
    public void saveInfluence(InfluenceFormDto influenceFormDto) {
        // date when you should add the information in db
        java.sql.Date date = java.sql.Date.valueOf(influenceFormDto.getInfluences().get(0).getDate());

        List<Influence> influenceList = new ArrayList<>();
        for (InfluenceDto inf : influenceFormDto.getInfluences()) {
            influenceList.add(getInfluenceFromDto(date, inf));
        }
        // sorting the influence by 'systems_id'
        Map<Long, Influence> influenceMap = new TreeMap<>();
        for (Influence inf : influenceList) {
            influenceMap.put(inf.getSystem().getId(), inf);
        }
        // add the result list to the db
        List<Influence> resultList = new ArrayList<>(influenceMap.values());

        influenceRepository.saveAll(resultList);
    }

    private Influence getInfluenceFromDto(java.sql.Date date, InfluenceDto inf) {
        System system = systemRepository.findByName(inf.getSystemName()).orElseThrow(EntityNotFoundException::new);
        return Influence.builder()
                .system(system)
                .date(date)
                .influence(inf.getInfluence())
                .state(State.valueOf(inf.getState().toUpperCase().replaceAll("\\s+", "_")))
                .build();
    }

    private InfluenceDto getInfluenceDtoNagii(List<Influence> influenceLastDayList,
                                              List<Influence> influenceDayBeforeLastList) {
        InfluenceDto influenceDtoNagii = modelMapper.map(influenceLastDayList.get(0), InfluenceDto.class);
        influenceDtoNagii.setState(influenceLastDayList.get(0).getState().getTitle());
        influenceDtoNagii.setChanges(
                influenceLastDayList.get(0).getInfluence() - influenceDayBeforeLastList.get(0).getInfluence()
        );

        return influenceDtoNagii;
    }

    private void calculateInfluenceWithNewSystem(List<Influence> influenceLastDayList,
                                                 List<Influence> influenceDayBeforeLastList,
                                                 List<InfluenceDto> influenceDtoResultList) {
        // Our faction get some new systems
        int howManySystemsAdded = influenceLastDayList.size() - influenceDayBeforeLastList.size();
        int systemCountDayBefore = influenceLastDayList.size() - howManySystemsAdded;
        calculateInfluence(influenceLastDayList,
                influenceDayBeforeLastList,
                influenceDtoResultList,
                systemCountDayBefore);
        for (int i = systemCountDayBefore; i < influenceLastDayList.size(); i++) {
            InfluenceDto influenceDto = modelMapper.map(influenceLastDayList.get(i), InfluenceDto.class);
            influenceDto.setState(influenceLastDayList.get(i).getState().getTitle());
            influenceDto.setChanges(influenceLastDayList.get(i).getInfluence());
            influenceDtoResultList.add(influenceDto);
        }
    }

    private void calculateInfluence(List<Influence> influenceLastDayList,
                                    List<Influence> influenceDayBeforeLastList,
                                    List<InfluenceDto> influenceDtoResultList,
                                    int size) {
        // Our faction don't have new systems
        for (int i = 1; i < size; i++) {
            InfluenceDto influenceDto = modelMapper.map(influenceLastDayList.get(i), InfluenceDto.class);
            influenceDto.setState(influenceLastDayList.get(i).getState().getTitle());
            influenceDto.setChanges(
                    influenceLastDayList.get(i).getInfluence() - influenceDayBeforeLastList.get(i).getInfluence()
            );
            influenceDtoResultList.add(influenceDto);
        }
    }
}
