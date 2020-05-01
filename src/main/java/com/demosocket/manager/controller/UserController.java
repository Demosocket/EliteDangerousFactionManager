package com.demosocket.manager.controller;

import com.demosocket.manager.dto.UserEditDto;
import com.demosocket.manager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public String findAll(Model model){
        model.addAttribute("users", userService.findAll());
        return "user-list";
    }

    @GetMapping("/edit/{id}")
    public String updateUserForm(@PathVariable("id") Long id, Model model){
        model.addAttribute("user", userService.findById(id));
        return "user-edit";
    }

    @PostMapping("/edit")
    public String updateUser(UserEditDto userEditDto){
        userService.saveUser(userEditDto);
        return "redirect:/users/all";
    }
}
