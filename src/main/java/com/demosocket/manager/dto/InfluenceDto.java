package com.demosocket.manager.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InfluenceDto {

    private String systemName;
    private String date;
    private String state;
    private Integer influence;
    private Integer changes;
}
