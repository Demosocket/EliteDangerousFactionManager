package com.demosocket.manager.dto;

import lombok.Data;
import lombok.Builder;
import lombok.AllArgsConstructor;

@Data
@Builder
@AllArgsConstructor
public class StateInformationDto {

    private Integer controlCount;
    private Integer noControlCount;
    private Integer expectationOfWarCount;
    private Integer expectationOfElectionsCount;
    private Integer warCount;
    private Integer electionsCount;
}
