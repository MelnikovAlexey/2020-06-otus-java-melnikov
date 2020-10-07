package org.otus.education.hw12.data.core.service;


import org.otus.education.hw12.data.core.model.User;

import java.util.List;
import java.util.Optional;

public interface DBServiceUser {

    long saveUser(User user);

    Optional<User> getUser(long id);

    Optional<User> findByLogin(String login);

    List<User> getUsers();
}
