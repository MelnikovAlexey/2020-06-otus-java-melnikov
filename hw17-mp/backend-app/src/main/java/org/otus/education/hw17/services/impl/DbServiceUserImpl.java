package org.otus.education.hw17.services.impl;

import org.otus.education.hw17.domain.User;
import org.otus.education.hw17.services.DBServiceUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.otus.education.hw17.repository.UserRepository;


import java.util.Collections;
import java.util.List;

@Service("defaultDbUserService")
public class DbServiceUserImpl implements DBServiceUser {
    private static final Logger logger = LoggerFactory.getLogger(DbServiceUserImpl.class);
    private final UserRepository dao;

    public DbServiceUserImpl(UserRepository dao) {
        this.dao = dao;
    }

    @Override
    public long save(User object) {
        try (var sessionManager = dao.getSessionManager()) {
            sessionManager.beginSession();
            try {
                var id = dao.insertOrUpdate(object);
                sessionManager.commitSession();
                logger.info("saved object with id {}: {}", id, object);
                return id;
            } catch (Exception e) {
                sessionManager.rollbackSession();
                throw new DbServiceException(e);
            }
        }
    }

    @Override
    public List<User> getAll() {
        try (var sessionManager = dao.getSessionManager()) {
            sessionManager.beginSession();
            try {
                return dao.selectAll();
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
            return Collections.emptyList();
        }
    }
}
