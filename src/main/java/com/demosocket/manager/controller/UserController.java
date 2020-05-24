package com.demosocket.manager.controller;

import com.demosocket.manager.dto.UserEditDto;
import com.demosocket.manager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


@Controller
@RequestMapping("/users")
public class UserController {

    private static final int CURRENT_PAGE = 1;
    private static final int PAGE_SIZE = 25;

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public String findAll(
            Model model,
            @RequestParam("page") Optional<Integer> page,
            @RequestParam("size") Optional<Integer> size) {

        int currentPage = page.orElse(CURRENT_PAGE);
        int pageSize = size.orElse(PAGE_SIZE);

        Page<UserEditDto> userPage = userService.findAll(
                PageRequest.of(currentPage - 1, pageSize, Sort.by("id").ascending()));
        model.addAttribute("userPage", userPage);

        int totalPages = userPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "user-list";
    }

    @GetMapping("/edit/{id}")
    public String updateUserForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.findById(id));
        return "user-edit";
    }

    @PostMapping("/edit")
    public String updateUser(UserEditDto userEditDto) {
        userService.saveUser(userEditDto);
        return "redirect:/users/all";
    }
}
