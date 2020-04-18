package com.demosocket.manager.controller;

import com.demosocket.manager.model.UserForm;
import com.demosocket.manager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {

    private UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/signup")
    public String registration() {
        return "signup-page";
    }

    @PostMapping(value = "/signup")
    public String createNewUser(UserForm userForm) {
        userService.saveUser(userForm);
        return "redirect:/";
    }
}
