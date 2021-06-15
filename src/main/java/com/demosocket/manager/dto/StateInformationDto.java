package com.demosocket.manager.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class StateInformationDto {

    Integer controlCount;
    Integer noControlCount;
    Integer expectationOfWarCount;
    Integer expectationOfElectionsCount;
    Integer warCount;
    Integer electionsCount;
}
