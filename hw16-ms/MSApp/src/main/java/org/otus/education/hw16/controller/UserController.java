package org.otus.education.hw16.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.otus.education.hw16.data.core.model.User;
import org.otus.education.hw16.data.core.service.DBServiceUser;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Slf4j
@Controller
public class UserController {

    private final DBServiceUser dbServiceUser;

    public UserController(DBServiceUser dbServiceUser) {
        this.dbServiceUser = dbServiceUser;
    }

    @GetMapping({"/users"})
    public String usersView(Model model) {
        List<User> users = dbServiceUser.getUsers();
        model.addAttribute("users", users);
        return "users";
    }
}
