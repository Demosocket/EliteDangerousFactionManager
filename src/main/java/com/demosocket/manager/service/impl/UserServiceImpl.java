package com.demosocket.manager.service.impl;

import com.demosocket.manager.dto.UserDto;
import com.demosocket.manager.dto.UserEditDto;
import com.demosocket.manager.model.Role;
import com.demosocket.manager.model.User;
import com.demosocket.manager.repository.UserRepository;
import com.demosocket.manager.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(PasswordEncoder passwordEncoder, UserRepository userRepository, ModelMapper modelMapper) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Page<UserEditDto> findAll(Pageable pageable) {
        Page<User> userPage = userRepository.findAll(pageable);
        List<User> userList = userPage.getContent();

        List<UserEditDto> userEditDtoList = new ArrayList<>();
        for (User user : userList) {
            String role = user.getRole().getTitle();
            UserEditDto userEditDto = modelMapper.map(user, UserEditDto.class);
            userEditDto.setRole(role);
            userEditDtoList.add(userEditDto);
        }
        return new PageImpl<>(userEditDtoList, pageable, userPage.getTotalElements());
    }

    @Override
    public UserEditDto findById(Long id) {
        User userFromDb = userRepository.findById(id).orElse(null);
        if (userFromDb == null) {
            return null;
        } else {
            UserEditDto userEditDto = modelMapper.map(userFromDb, UserEditDto.class);
            userEditDto.setRole(userFromDb.getRole().getTitle());
            return userEditDto;
        }
    }

    @Override
    public void saveNewUser(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        user.setHashPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRole(Role.COMRADE);
        user.setEnabled(true);
        userRepository.save(user);
    }

    @Override
    public void saveUser(UserEditDto userEditDto) {
        User userFromDB = userRepository.findByUsername(userEditDto.getUsername());
        User user = modelMapper.map(userEditDto, User.class);
        user.setHashPassword(userFromDB.getHashPassword());
        user.setRole(Role.valueOf(userEditDto.getRole().toUpperCase()));
        userRepository.save(user);
    }
}
