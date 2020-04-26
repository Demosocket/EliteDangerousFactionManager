package com.demosocket.manager.controller;

import com.demosocket.manager.dto.UserDto;
import com.demosocket.manager.model.User;
import com.demosocket.manager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class RegisterController {

    private final UserService userService;

    @Autowired
    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/signup")
    public String registration(UserDto userDto) {
        return "signup-page";
    }

    @PostMapping("/signup")
    public String registrationNewUser(UserDto userDto, Model model) {
        User userFromDb = userService.findByUsername(userDto.getUsername());
        if (userFromDb != null) {
            model.addAttribute("userExist", "User already Exists");
            return "signup-page";
        } else userService.saveUser(userDto);
        return "redirect:/";
    }
}
