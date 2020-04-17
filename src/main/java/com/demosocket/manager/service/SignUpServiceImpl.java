package com.demosocket.manager.service;

import com.demosocket.manager.model.UserForm;
import com.demosocket.manager.model.Role;
import com.demosocket.manager.model.State;
import com.demosocket.manager.model.User;
import com.demosocket.manager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SignUpServiceImpl implements SignUpService {

//    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    @Autowired
    public SignUpServiceImpl(/*PasswordEncoder passwordEncoder, */UserRepository userRepository) {
//        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Override
    public void signUp(UserForm userForm) {
//        String hashPassword = passwordEncoder.encode(userForm.getPassword());

        User user = new User();
        user.setUsername(userForm.getUsername());
        user.setHashPassword(userForm.getPassword()); //
        user.setRole(Role.USER);
        user.setState(State.ACTIVE);

        userRepository.save(user);
    }
}
