package org.otus.education.hw16.data.core.dao;

import org.otus.education.hw16.data.core.model.User;
import org.otus.education.hw16.data.core.sessionmanager.SessionManager;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    Optional<User> findById(long id);

    long insertUser(User user);

    void updateUser(User user);

    void insertOrUpdate(User user);

    SessionManager getSessionManager();

    Optional<User> findByLogin(String login);

    List<User> getUsers();

    boolean removeUserById(long id);
}
