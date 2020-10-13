package org.otus.education.hw12.web.services;

import org.eclipse.jetty.security.AbstractLoginService;
import org.eclipse.jetty.util.security.Password;
import org.otus.education.hw12.data.core.service.DBServiceUser;

import java.util.Objects;

public class LoginByDBServiceUser extends AbstractLoginService {

    private final DBServiceUser dbServiceUser;

    public LoginByDBServiceUser(DBServiceUser dbServiceUser) {
        if (Objects.isNull(dbServiceUser)) {
            throw new IllegalArgumentException("dbServiceUser can't be null");
        }
        this.dbServiceUser = dbServiceUser;
    }

    @Override
    protected String[] loadRoleInfo(UserPrincipal user) {
        return "admin".equals(user.getName()) ? new String[]{"admin", "user"} : new String[]{"user"};
    }

    @Override
    protected UserPrincipal loadUserInfo(String username) {
        return dbServiceUser.findByLogin(username)
                .map(user -> new UserPrincipal(
                        user.getLogin(),
                        new Password(user.getPassword())))
                .orElse(null);
    }

}
