package com.demosocket.manager.service.impl;

import com.demosocket.manager.dto.InfluenceDto;
import com.demosocket.manager.dto.InfluenceFormDto;
import com.demosocket.manager.model.Influence;
import com.demosocket.manager.model.State;
import com.demosocket.manager.repository.InfluenceRepository;
import com.demosocket.manager.repository.SystemRepository;
import com.demosocket.manager.service.InfluenceService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class InfluenceServiceImpl implements InfluenceService {

    private final InfluenceRepository influenceRepository;
    private final SystemRepository systemRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public InfluenceServiceImpl(InfluenceRepository influenceRepository,
                                SystemRepository systemRepository,
                                ModelMapper modelMapper) {
        this.influenceRepository = influenceRepository;
        this.systemRepository = systemRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<Date> findTwoLastDays() {
        return influenceRepository.findTwoLastDays();
    }

    @Override
    public List<InfluenceDto> findInfluenceDtoLastDay() {
//        Find two last days in db
        List<Date> lastTwoDays = influenceRepository.findTwoLastDays();
//        Make query to find 2 lists of influences
        List<Influence> influenceLastDayList = influenceRepository.findAllByDateOrderById(lastTwoDays.get(0));
        List<Influence> influenceDayBeforeLastList = influenceRepository.findAllByDateOrderById(lastTwoDays.get(1));

        List<InfluenceDto> influenceDtoResultList = new ArrayList<>();
//        InfluenceDto for Nagii - main system
        InfluenceDto influenceDtoNagii = modelMapper.map(influenceLastDayList.get(0), InfluenceDto.class);
        influenceDtoNagii.setState(influenceLastDayList.get(0).getState().getTitle());
        influenceDtoNagii.setChanges(
                influenceLastDayList.get(0).getInfluence()-influenceDayBeforeLastList.get(0).getInfluence()
        );
        if (influenceLastDayList.size() == influenceDayBeforeLastList.size()) {
//            Our faction don't have new systems(Common day)
            for (int i = 1; i < influenceLastDayList.size(); i++) {
                InfluenceDto influenceDto = modelMapper.map(influenceLastDayList.get(i), InfluenceDto.class);
                influenceDto.setState(influenceLastDayList.get(i).getState().getTitle());
                influenceDto.setChanges(
                        influenceLastDayList.get(i).getInfluence()-influenceDayBeforeLastList.get(i).getInfluence()
                );
                influenceDtoResultList.add(influenceDto);
            }
        } else if (influenceLastDayList.size() > influenceDayBeforeLastList.size()) {
//            Our faction get some new systems(Funny day)
            int howManySystemsAdded = influenceLastDayList.size()-influenceDayBeforeLastList.size();
            for (int i = 1; i < influenceLastDayList.size()-howManySystemsAdded; i++) {
                InfluenceDto influenceDto = modelMapper.map(influenceLastDayList.get(i), InfluenceDto.class);
                influenceDto.setState(influenceLastDayList.get(i).getState().getTitle());
                influenceDto.setChanges(
                        influenceLastDayList.get(i).getInfluence()-influenceDayBeforeLastList.get(i).getInfluence()
                );
                influenceDtoResultList.add(influenceDto);
            }
            for (int i = influenceLastDayList.size()-howManySystemsAdded; i < influenceLastDayList.size(); i++) {
                InfluenceDto influenceDto = modelMapper.map(influenceLastDayList.get(i), InfluenceDto.class);
                influenceDto.setState(influenceLastDayList.get(i).getState().getTitle());
                influenceDto.setChanges(
                        influenceLastDayList.get(i).getInfluence()
                );
                influenceDtoResultList.add(influenceDto);
            }
        }
//        sort the resulting list for easy presentation
        influenceDtoResultList.sort(Comparator.comparing(InfluenceDto::getSystemName));
//        and set Nagii at 0 position
        influenceDtoResultList.add(0, influenceDtoNagii);

        return influenceDtoResultList;
    }

    @Override
    public void saveInfluence(InfluenceFormDto influenceFormDto) {
//        day when you should add the information in db
        java.sql.Date day = java.sql.Date.valueOf(influenceFormDto.getInfluences().get(0).getDate());

        List<Influence> influenceList = new ArrayList<>();
        for (InfluenceDto inf : influenceFormDto.getInfluences()) {
            Influence influence = new Influence();
            influence.setSystem(systemRepository.findByName(inf.getSystemName()));
            influence.setDate(day);
            influence.setInfluence(inf.getInfluence());
            influence.setState(State.valueOf(
                    inf.getState().toUpperCase().replaceAll("\\s+", "_")
            ));
            influenceList.add(influence);
        }
//        sorting the influence by 'systems_id'
        Map<Long, Influence> influenceMap = new TreeMap<>();
        for (Influence inf : influenceList) {
            influenceMap.put(inf.getSystem().getId(), inf);
        }
//        add the result list to the db
        List<Influence> resultList = new ArrayList<>(influenceMap.values());

        influenceRepository.saveAll(resultList);
    }
}
