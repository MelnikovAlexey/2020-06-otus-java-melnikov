package org.otus.education.jdbc.dao;

import org.otus.education.core.dao.UserDao;
import org.otus.education.core.model.User;
import org.otus.education.core.sessionmanager.SessionManager;
import org.otus.education.jdbc.mapper.JdbcMapper;
import org.otus.education.jdbc.sessionmanager.SessionManagerJdbc;

import java.util.Optional;

public class UserDaoJdbcMapper implements UserDao {

    private final JdbcMapper<User> mapper;

    public UserDaoJdbcMapper(JdbcMapper<User> mapper) {
        this.mapper = mapper;
    }

    @Override
    public Optional<User> findById(long id) {
        return mapper.findById(id);
    }

    @Override
    public long insertUser(User user) {
        return mapper.insert(user);
    }

    @Override
    public void updateUser(User user) {
        mapper.update(user);
    }

    @Override
    public void insertOrUpdate(User user) {
        mapper.insertOrUpdate(user);
    }

/*    @Override
    public SessionManager getSessionManager() {
        return sessionManager;
    }*/
}
