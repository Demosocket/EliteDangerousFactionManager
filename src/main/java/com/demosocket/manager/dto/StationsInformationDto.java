package com.demosocket.manager.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class StationsInformationDto {

    private Integer totalOrbitLargeControl;
    private Integer totalOrbitLarge;
    private Integer totalOrbitMediumControl;
    private Integer totalOrbitMedium;
    private Integer totalPlanetLargeControl;
    private Integer totalPlanetLarge;
    private Integer totalPlanetBaseControl;
    private Integer totalPlanetBase;
}
