package com.demosocket.manager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InfluenceInformationDto {

    private String day;
    private Integer totalChanges;
}
