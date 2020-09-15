package org.otus.education.jdbc.dao;

import org.otus.education.core.dao.AccountDao;
import org.otus.education.core.model.Account;
import org.otus.education.core.sessionmanager.SessionManager;
import org.otus.education.jdbc.mapper.JdbcMapper;
import org.otus.education.jdbc.sessionmanager.SessionManagerJdbc;

import java.sql.Connection;
import java.util.Optional;

public class AccountDaoJdbcMapper implements AccountDao {

    private final JdbcMapper<Account> mapper;
    private final SessionManagerJdbc sessionManager;

    public AccountDaoJdbcMapper(JdbcMapper<Account> mapper, SessionManagerJdbc sessionManager) {
        this.mapper = mapper;
        this.sessionManager = sessionManager;
    }

    @Override
    public Optional<Account> findByNo(long no) {
        return mapper.findById(no,getConnection());
    }

    @Override
    public long insertAccount(Account account) {
        return mapper.insert(account,getConnection());
    }

    @Override
    public void updateAccount(Account account) {
        mapper.update(account,getConnection());
    }

    @Override
    public void insertOrUpdate(Account account) {
        mapper.insertOrUpdate(account,getConnection());
    }

    @Override
    public SessionManager getSessionManager() {
        return sessionManager;
    }

    private Connection getConnection(){
        return sessionManager.getCurrentSession().getConnection();
    }
}
