package org.otus.education.data.core.service;


import org.otus.education.data.core.model.User;

import java.util.Optional;

public interface DBServiceUser {

    long saveUser(User user);

    Optional<User> getUser(long id);
}
