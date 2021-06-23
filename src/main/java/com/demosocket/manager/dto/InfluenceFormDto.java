package com.demosocket.manager.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class InfluenceFormDto {

    private final List<InfluenceDto> influences;
}
