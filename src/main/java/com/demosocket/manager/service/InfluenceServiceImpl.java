package com.demosocket.manager.service;

import com.demosocket.manager.dto.InfluenceDto;
import com.demosocket.manager.model.Influence;
import com.demosocket.manager.repository.InfluenceRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class InfluenceServiceImpl implements InfluenceService {

    private final InfluenceRepository influenceRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public InfluenceServiceImpl(InfluenceRepository influenceRepository, ModelMapper modelMapper) {
        this.influenceRepository = influenceRepository;
        this.modelMapper = modelMapper;
    }

//    @Override
//    public List<InfluenceDto> findAll() {
//        List<Influence> influenceList = influenceRepository.findAll();
//        List<InfluenceDto> influenceDtoList = new ArrayList<>();
//        for (Influence influence : influenceList) {
//            InfluenceDto influenceDto = modelMapper.map(influence, InfluenceDto.class);
//            influenceDto.setState(influence.getState().getTitle());
//            influenceDtoList.add(influenceDto);
//        }
//        return influenceDtoList;
//    }

    @Override
    public Influence findFirstByOrderByDayDesc() {
        return influenceRepository.findFirstByOrderByDayDesc();
    }

//    @Override
//    public List<InfluenceDto> findByDay(Date date) {
//        List<Influence> influenceList = influenceRepository.findByDay(influenceRepository.findFirstByOrderByDayDesc().getDay());
//        List<InfluenceDto> influenceDtoList = new ArrayList<>();
//        for (Influence influence : influenceList) {
//            InfluenceDto influenceDto = modelMapper.map(influence, InfluenceDto.class);
//            influenceDto.setState(influence.getState().getTitle());
//            influenceDtoList.add(influenceDto);
//        }
//        return influenceDtoList;
//    }

    @Override
    public List<Date> findMyQuery() {
        return influenceRepository.findMyQuery();
    }

    @Override
    public List<InfluenceDto> findLastInfluence() {
        List<Influence> influenceLastDayList = influenceRepository.findAllByDayOrderById(influenceRepository.findMyQuery().get(0));
        List<Influence> influenceDayBeforeLastList = influenceRepository.findAllByDayOrderById(influenceRepository.findMyQuery().get(1));

        List<InfluenceDto> influenceDtoList = new ArrayList<>();

//        For Nagii
        InfluenceDto influenceDtoNagii = modelMapper.map(influenceLastDayList.get(0), InfluenceDto.class);
        influenceDtoNagii.setState(influenceLastDayList.get(0).getState().getTitle());
        influenceDtoNagii.setChanges(
                influenceLastDayList.get(0).getInfluence()-influenceDayBeforeLastList.get(0).getInfluence()
        );

        if (influenceLastDayList.size() == influenceDayBeforeLastList.size()) {
//            Обычный день
            for (int i = 1; i < influenceLastDayList.size(); i++) {
                InfluenceDto influenceDto = modelMapper.map(influenceLastDayList.get(i), InfluenceDto.class);
                influenceDto.setState(influenceLastDayList.get(i).getState().getTitle());
                influenceDto.setChanges(
                        influenceLastDayList.get(i).getInfluence()-influenceDayBeforeLastList.get(i).getInfluence()
                );
                influenceDtoList.add(influenceDto);
            }
        } else if (influenceLastDayList.size() > influenceDayBeforeLastList.size()) {
//            Веселый день
        } else {
//            Грусный день
        }

        influenceDtoList.sort(Comparator.comparing(InfluenceDto::getSystemName));
//        Set Nagii at 0
        influenceDtoList.add(0, influenceDtoNagii);

        return influenceDtoList;
    }
}
