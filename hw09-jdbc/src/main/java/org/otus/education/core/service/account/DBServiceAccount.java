package org.otus.education.core.service.account;

import org.otus.education.core.model.Account;

import java.util.Optional;

public interface DBServiceAccount {
    long createAccount(Account account);

    Optional<Account> getAccount(long id);

    void updateAccount(Account account);

}
