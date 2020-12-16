package org.otus.education.hw16.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.otus.education.hw16.data.core.model.User;
import org.otus.education.hw16.data.core.service.DBServiceUser;

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


    @DeleteMapping("{id}")
    public void removeUserById(@PathVariable(name = "id") long id) {
        dbServiceUser.removeUserById(id);
    }

    @PostMapping
    public void addUser(@RequestBody User user) {
        dbServiceUser.saveUser(user);
    }
}
