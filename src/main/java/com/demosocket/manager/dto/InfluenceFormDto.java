package com.demosocket.manager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class InfluenceFormDto {

    private List<InfluenceDto> influences;
}
