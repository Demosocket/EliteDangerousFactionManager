package com.demosocket.manager.dto;

import com.demosocket.manager.model.Role;
import lombok.Data;

@Data
public class UserEditDto {

    private Integer id;

    private String username;

    private Role Role;

    private Boolean enabled;
}
