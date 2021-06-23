package com.demosocket.manager.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SystemDto {

    private Long id;
    private Integer number;
    private String name;
    private Long population;
    private String primaryEconomy;
    private String secondaryEconomy;
    private Integer orbitLargeControl;
    private Integer orbitLarge;
    private Integer orbitMediumControl;
    private Integer orbitMedium;
    private Integer planetLargeControl;
    private Integer planetLarge;
    private Integer planetBaseControl;
    private Integer planetBase;
    private String expansionDate;
}
