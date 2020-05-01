package com.demosocket.manager.service;

import com.demosocket.manager.dto.UserDto;
import com.demosocket.manager.dto.UserEditDto;
import com.demosocket.manager.model.User;

import java.util.List;

public interface UserService {

    List<UserEditDto> findAll();
    UserEditDto findById(Long id);
    User findByUsername(String username);
    void saveNewUser(UserDto userDto);
    void saveUser(UserEditDto userEditDto);
}
