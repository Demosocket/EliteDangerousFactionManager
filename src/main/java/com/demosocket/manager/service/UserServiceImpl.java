package com.demosocket.manager.service;

import com.demosocket.manager.dto.UserDto;
import com.demosocket.manager.dto.UserEditDto;
import com.demosocket.manager.model.Role;
import com.demosocket.manager.model.User;
import com.demosocket.manager.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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
    public UserEditDto findById(Long id) {
        User userFromDb = userRepository.findById(id).orElse(null);
        UserEditDto userEditDto = modelMapper.map(userFromDb, UserEditDto.class);
        assert userFromDb != null;
        userEditDto.setRole(userFromDb.getRole().getTitle());
        return userEditDto;
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<UserEditDto> findAll() {
        List<UserEditDto> userEditDtoList = new ArrayList<>();
        List<User> userList = userRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
        for (User user : userList) {
            String role = user.getRole().getTitle();
            UserEditDto userEditDto = modelMapper.map(user, UserEditDto.class);
            userEditDto.setRole(role);
            userEditDtoList.add(userEditDto);
        }
        return userEditDtoList;
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
