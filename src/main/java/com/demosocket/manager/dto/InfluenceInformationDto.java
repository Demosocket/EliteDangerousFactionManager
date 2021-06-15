package com.demosocket.manager.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class InfluenceInformationDto {

    private String day;
    private Integer totalChanges;
}
