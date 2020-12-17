package org.otus.education.hw16.controller;

import lombok.extern.slf4j.Slf4j;
import org.otus.education.hw16.data.core.model.User;
import org.otus.education.hw16.data.core.service.DBServiceUser;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping(value = "/api/user")
public class UserRestController {

    private final DBServiceUser dbServiceUser;

    public UserRestController(DBServiceUser dbServiceUser) {
        this.dbServiceUser = dbServiceUser;
    }

    @GetMapping
    public List<User> getUsers() {
        return dbServiceUser.getUsers();
    }

    @PostMapping
    public void addUser(@RequestBody User user) {
        dbServiceUser.saveUser(user);
    }
}
