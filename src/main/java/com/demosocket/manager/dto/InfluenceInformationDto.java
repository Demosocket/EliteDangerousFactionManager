package com.demosocket.manager.dto;

import lombok.Data;
import lombok.Builder;
import lombok.AllArgsConstructor;

@Data
@Builder
@AllArgsConstructor
public class InfluenceInformationDto {

    private String day;
    private Integer totalChanges;
}
