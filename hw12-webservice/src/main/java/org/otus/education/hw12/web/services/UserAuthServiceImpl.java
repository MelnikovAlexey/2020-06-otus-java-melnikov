package org.otus.education.hw12.web.services;

import org.otus.education.hw12.data.core.model.User;
import org.otus.education.hw12.data.core.service.DBServiceUser;

import java.util.Optional;

public class UserAuthServiceImpl implements UserAuthService {

    private static final String ADMIN_ROLE = "admin";

    private final DBServiceUser dbServiceUser;

    public UserAuthServiceImpl(DBServiceUser dbServiceUser) {
        this.dbServiceUser = dbServiceUser;
    }

    @Override
    public boolean authenticate(String login, String password) {
        Optional<User> optional = dbServiceUser.findByLogin(login);
        if (optional.isPresent()) {
            User user = optional.get();
            return password.equals(user.getPassword()) && ADMIN_ROLE.equals(user.getRole());
        }
        return false;
    }
}
