package com.demosocket.manager.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserEditDto {

    private Long id;
    private String username;
    private String role;
    private Boolean enabled;
}
