package com.demosocket.manager.dto;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StationsInformationDto {

    private Integer totalOrbitLargeControl = 0;
    private Integer totalOrbitLarge = 0;
    private Integer totalOrbitMediumControl = 0;
    private Integer totalOrbitMedium = 0;
    private Integer totalPlanetLargeControl = 0;
    private Integer totalPlanetLarge = 0;
    private Integer totalPlanetBaseControl = 0;
    private Integer totalPlanetBase = 0;

    public void addOrbitLargeControl(int value) {
        this.totalOrbitLargeControl += value;
    }
    public void addOrbitLarge(int value) {
        this.totalOrbitLarge += value;
    }

    public void addOrbitMediumControl(int value) {
        this.totalOrbitMediumControl += value;
    }

    public void addOrbitMedium(int value) {
        this.totalOrbitMedium += value;
    }

    public void addPlanetLargeControl(int value) {
        this.totalPlanetLargeControl += value;
    }

    public void addPlanetLarge(int value) {
        this.totalPlanetLarge += value;
    }

    public void addPlanetBaseControl(int value) {
        this.totalPlanetBaseControl += value;
    }

    public void addPlanetBase(int value) {
        this.totalPlanetBase += value;
    }
}
