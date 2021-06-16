package com.demosocket.manager.service;

import com.demosocket.manager.dto.UserDto;
import com.demosocket.manager.dto.UserEditDto;
import com.demosocket.manager.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    User findByUsername(String username);

    Page<UserEditDto> findAll(Pageable pageable);

    UserEditDto findById(Long id);

    void saveNewUser(UserDto userDto);

    void updateUser(UserEditDto userEditDto);
}
