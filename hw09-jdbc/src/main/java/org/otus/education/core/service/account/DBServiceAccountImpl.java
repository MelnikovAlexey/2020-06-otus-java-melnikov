package org.otus.education.core.service.account;

import org.otus.education.core.dao.AccountDao;
import org.otus.education.core.model.Account;
import org.otus.education.core.service.DbServiceUserImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class DBServiceAccountImpl implements DBServiceAccount {
    private static final Logger logger = LoggerFactory.getLogger(DBServiceAccountImpl.class);

    private final AccountDao accountDao;

    public DBServiceAccountImpl(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @Override
    public long createAccount(Account account) {
        try(var sessionManager = accountDao.getSessionManager()){
            sessionManager.beginSession();
            try {
                var accountNo = accountDao.insertAccount(account);
                sessionManager.commitSession();

                logger.info("Create account: {}",accountNo);
                return accountNo;
            } catch (Exception e){
                sessionManager.rollbackSession();
                throw new DbServiceAccountException(e.getMessage());
            }

        }
    }

    @Override
    public Optional<Account> getAccount(long id) {
        try(var sessionManager = accountDao.getSessionManager()) {
            sessionManager.beginSession();
            try {
                Optional<Account> accountOptional = accountDao.findByNo(id);
                logger.info("Account: {}",accountOptional.orElse(null));
                return accountOptional;

            }catch (Exception e){
                logger.error(e.getMessage());
                sessionManager.rollbackSession();
            }
            return Optional.empty();
        }
    }

    @Override
    public void updateAccount(Account account) {
        try(var sessionManager = accountDao.getSessionManager()) {
            sessionManager.beginSession();
            try {
                accountDao.updateAccount(account);
                sessionManager.commitSession();
                logger.info("update account: {}",account);
            }catch (Exception e){
                logger.error(e.getMessage());
                sessionManager.rollbackSession();
            }
        }

    }
}
