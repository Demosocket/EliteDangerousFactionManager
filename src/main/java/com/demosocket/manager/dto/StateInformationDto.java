package com.demosocket.manager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StateInformationDto {

    Integer controlCount;
    Integer noControlCount;
    Integer expectationOfWarCount;
    Integer expectationOfElectionsCount;
    Integer warCount;
    Integer electionsCount;
}
