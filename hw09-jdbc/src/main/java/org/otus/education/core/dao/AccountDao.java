package org.otus.education.core.dao;

import org.otus.education.core.model.Account;
import org.otus.education.core.sessionmanager.SessionManager;

import java.util.Optional;

public interface AccountDao {

    Optional<Account> findByNo(long no);

    long insertAccount(Account account);

    void updateAccount(Account account);

    void insertOrUpdate(Account account);

    SessionManager getSessionManager();
}
