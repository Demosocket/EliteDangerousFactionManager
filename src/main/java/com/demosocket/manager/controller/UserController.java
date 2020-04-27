package com.demosocket.manager.controller;

import com.demosocket.manager.dto.UserEditDto;
import com.demosocket.manager.model.User;
import com.demosocket.manager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

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
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "user-list";
    }

    @GetMapping("/edit/{id}")
    public String updateUserForm(@PathVariable("id") Integer id, Model model){
        User user = userService.findById(id);
        UserEditDto userEditDto = new UserEditDto();
        userEditDto.setId(user.getId());
        userEditDto.setUsername(user.getUsername());
        userEditDto.setRole(user.getRole());
        userEditDto.setEnabled(user.isEnabled());
        model.addAttribute("user", userEditDto);
        return "user-edit";
    }

    @PostMapping("/edit")
    public String updateUser(UserEditDto userEditDto){
        User user = userService.findById(userEditDto.getId());
        user.setRole(userEditDto.getRole());
        user.setEnabled(userEditDto.getEnabled());
        userService.saveUser(user);
        return "redirect:/users/all";
    }
}
