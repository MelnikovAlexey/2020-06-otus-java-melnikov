package org.otus.education.hw17.services;


import org.otus.education.hw17.domain.User;

import java.util.List;

public interface DBServiceUser {

    long save(User user);

    List<User> getAll();
}
