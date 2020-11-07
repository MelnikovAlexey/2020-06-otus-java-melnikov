package org.otus.education.hw14.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.otus.education.hw14.data.core.model.User;
import org.otus.education.hw14.data.core.service.DBServiceUser;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

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
        return "users.html";
    }

    @GetMapping("/add")
    public String userCreateView(Model model) {
        model.addAttribute("user", new User());
        return "userAdd.html";
    }

    @PostMapping("/save")
    public RedirectView userSave(@ModelAttribute User user) {
        dbServiceUser.saveUser(user);
        return new RedirectView("/users", true);
    }
}
