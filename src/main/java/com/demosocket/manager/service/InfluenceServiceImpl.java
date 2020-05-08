package com.demosocket.manager.service;

import com.demosocket.manager.dto.InfluenceDto;
import com.demosocket.manager.dto.InfluenceFormDto;
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


    @Override
    public List<Date> findTwoLastDays() {
        return influenceRepository.findTwoLastDays();
    }

    @Override
    public List<InfluenceDto> findInfluenceDtoLastDay() {
        List<Influence> influenceLastDayList = influenceRepository.findAllByDayOrderById(influenceRepository.findTwoLastDays().get(0));
        List<Influence> influenceDayBeforeLastList = influenceRepository.findAllByDayOrderById(influenceRepository.findTwoLastDays().get(1));

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
        /*Here need check the condition when (influenceLastDayList.size() < influenceDayBeforeLastList.size()) in the
        future version of application(Sad day)*/

//        sort the resulting list for easy presentation
        influenceDtoResultList.sort(Comparator.comparing(InfluenceDto::getSystemName));
//        and set Nagii at 0 position
        influenceDtoResultList.add(0, influenceDtoNagii);

        return influenceDtoResultList;
    }

    @Override
    public void saveInfluence(InfluenceFormDto influenceFormDto, Date date) {
//        Сделай что-нибудь с тем, что пришло из  Thymeleaf, Если конечно оно придет
    }
}
