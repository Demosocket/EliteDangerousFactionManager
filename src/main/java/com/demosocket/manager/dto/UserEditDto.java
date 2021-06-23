package com.demosocket.manager.dto;

import lombok.Data;

@Data
public class UserEditDto {

    private Long id;
    private String username;
    private String role;
    private Boolean enabled;
}
