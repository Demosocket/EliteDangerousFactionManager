package com.demosocket.manager.controller;

import com.demosocket.manager.dto.UserDto;
import com.demosocket.manager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class RegisterController {

    private UserService userService;

    @Autowired
    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/signup")
    public String registration(UserDto userDto) {
//        UserDto userDto = new UserDto();
//        model.addAttribute("userDto", new User());
        return "signup-page";
    }

    @PostMapping("/signup")
    public String registrationNewUser(UserDto userDto) {
        userService.saveUser(userDto);
        return "redirect:/";
    }
}
