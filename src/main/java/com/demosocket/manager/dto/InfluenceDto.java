package com.demosocket.manager.dto;

import lombok.Data;

@Data
public class InfluenceDto {

    private String systemName;
    private String day;
    private String state;
    private Integer influence;
    private Integer changes;
}
