package com.demosocket.manager.service;

import com.demosocket.manager.dto.UserDto;
import com.demosocket.manager.model.System;
import com.demosocket.manager.model.User;

import java.util.List;

public interface UserService {

    List<User> findAll();
    void saveUser(UserDto userDto);
    User findByUsername(String username);
}
