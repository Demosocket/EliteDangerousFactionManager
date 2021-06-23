package com.demosocket.manager.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class InfluenceInformationDto {

    private String day;
    private Integer totalChanges;
}
