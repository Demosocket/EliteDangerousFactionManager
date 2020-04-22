package com.demosocket.manager.service;

import com.demosocket.manager.dto.UserDto;
import com.demosocket.manager.model.User;

public interface UserService {
    void saveUser(UserDto userDto);
    User findByUsername(String username);
}
