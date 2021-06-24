package com.demosocket.manager.dto;

import com.demosocket.manager.model.Influence;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
public class InfluenceCalculateDto {

    private List<Influence> influenceLastDayList;
    private List<Influence> influenceDayBeforeLastList;
    private List<InfluenceDto> influenceDtoResultList;
}
