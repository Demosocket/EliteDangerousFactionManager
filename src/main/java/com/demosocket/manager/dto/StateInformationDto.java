package com.demosocket.manager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StateInformationDto {

    Integer controlCount;
    Integer noControlCount;
    Integer expectationOfWarCount;
    Integer expectationOfElectionsCount;
    Integer warCount;
    Integer electionsCount;
}
