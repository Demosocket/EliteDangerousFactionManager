package com.demosocket.manager.dto;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StateInformationDto {

    private Integer controlCount = 0;
    private Integer noControlCount = 0;
    private Integer expectationOfWarCount = 0;
    private Integer expectationOfElectionsCount = 0;
    private Integer warCount = 0;
    private Integer electionsCount = 0;

    public void incrementControlCount() {
        this.controlCount++;
    }

    public void incrementNoControlCount() {
        this.noControlCount++;
    }

    public void incrementExpectationOfWarCount() {
        this.expectationOfWarCount++;
    }

    public void incrementExpectationOfElectionsCount() {
        this.expectationOfElectionsCount++;
    }

    public void incrementWarCount() {
        this.warCount++;
    }

    public void incrementElectionsCount() {
        this.electionsCount++;
    }
}
