package com.demosocket.manager.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
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
