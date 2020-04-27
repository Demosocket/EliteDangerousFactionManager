package com.demosocket.manager.service;

import com.demosocket.manager.dto.UserDto;
import com.demosocket.manager.model.User;

import java.util.List;

public interface UserService {

    List<User> findAll();
    void saveNewUser(UserDto userDto);
    User findByUsername(String username);
    User findById(Integer id);
    void saveUser(User user);
}
