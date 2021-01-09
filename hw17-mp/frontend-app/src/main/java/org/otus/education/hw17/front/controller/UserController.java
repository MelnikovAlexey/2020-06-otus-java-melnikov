package org.otus.education.hw17.front.controller;

import lombok.extern.slf4j.Slf4j;
import org.otus.education.hw17.front.dto.UserDto;
import org.otus.education.hw17.front.services.FrontendService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Slf4j
@Controller
public class UserController {

    private final FrontendService frontendService;

    public UserController(FrontendService frontendService) {
        this.frontendService = frontendService;
    }

    @GetMapping({"/users"})
    public String usersView(Model model) {
        List<UserDto> users = frontendService.findAllUsers();
        model.addAttribute("users", users);
        return "users";
    }
}
