package com.demosocket.manager.dto;

import lombok.Data;

import java.util.Date;

@Data
public class SystemDto {

    private Long id;
    private Integer systemNum;
    private String systemName;
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
    private Date expDate;
}
