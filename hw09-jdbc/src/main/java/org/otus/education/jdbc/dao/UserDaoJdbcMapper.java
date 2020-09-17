package org.otus.education.jdbc.dao;

import org.otus.education.core.dao.UserDao;
import org.otus.education.core.model.User;
import org.otus.education.core.sessionmanager.SessionManager;
import org.otus.education.jdbc.mapper.JdbcMapper;
import org.otus.education.jdbc.sessionmanager.SessionManagerJdbc;

import java.sql.Connection;
import java.util.Optional;

public class UserDaoJdbcMapper implements UserDao {

    private final JdbcMapper<User> mapper;
    private final SessionManagerJdbc sessionManager;

    public UserDaoJdbcMapper(JdbcMapper<User> mapper, SessionManagerJdbc sessionManager) {
        this.mapper = mapper;
        this.sessionManager = sessionManager;
    }

    @Override
    public Optional<User> findById(long id) {
        return mapper.findById(id, getConnection());
    }

    @Override
    public long insertUser(User user) {
        return mapper.insert(user, getConnection());
    }

    @Override
    public void updateUser(User user) {
        mapper.update(user, getConnection());
    }

    @Override
    public void insertOrUpdate(User user) {
        mapper.insertOrUpdate(user, getConnection());
    }

    @Override
    public SessionManager getSessionManager() {
        return sessionManager;
    }

    private Connection getConnection() {
        return sessionManager.getCurrentSession().getConnection();
    }
}
