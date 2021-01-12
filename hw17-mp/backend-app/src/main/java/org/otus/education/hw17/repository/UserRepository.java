package org.otus.education.hw17.repository;


import org.otus.education.hw17.domain.User;
import org.otus.education.hw17.components.SessionManager;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    Optional<User> findById(long id);

    long insert(User object);

    void update(User object);

    long insertOrUpdate(User object);

    List<User> selectAll();

    SessionManager getSessionManager();
}
