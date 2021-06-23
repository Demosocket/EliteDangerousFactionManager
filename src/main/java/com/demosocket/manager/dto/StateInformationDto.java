package com.demosocket.manager.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class StateInformationDto {

    private Integer controlCount;
    private Integer noControlCount;
    private Integer expectationOfWarCount;
    private Integer expectationOfElectionsCount;
    private Integer warCount;
    private Integer electionsCount;
}
