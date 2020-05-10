package com.demosocket.manager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
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
