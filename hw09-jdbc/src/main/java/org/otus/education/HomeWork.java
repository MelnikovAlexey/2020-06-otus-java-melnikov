package org.otus.education;

import org.flywaydb.core.Flyway;
import org.otus.education.core.dao.AccountDao;
import org.otus.education.core.dao.UserDao;
import org.otus.education.core.model.Account;
import org.otus.education.core.model.User;
import org.otus.education.core.service.DbServiceUserImpl;
import org.otus.education.core.service.account.DBServiceAccountImpl;
import org.otus.education.h2.DataSourceH2;
import org.otus.education.jdbc.DbExecutor;
import org.otus.education.jdbc.DbExecutorImpl;
import org.otus.education.jdbc.dao.AccountDaoJdbcMapper;
import org.otus.education.jdbc.dao.UserDaoJdbcMapper;
import org.otus.education.jdbc.mapper.EntityClassMetaData;
import org.otus.education.jdbc.mapper.EntitySQLMetaData;
import org.otus.education.jdbc.mapper.JdbcMapper;
import org.otus.education.jdbc.mapper.impl.EntityClassMetaDataImpl;
import org.otus.education.jdbc.mapper.impl.EntitySQLMetaDataImpl;
import org.otus.education.jdbc.mapper.impl.JdbcMapperImpl;
import org.otus.education.jdbc.sessionmanager.SessionManagerJdbc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.Connection;
import java.util.Optional;


public class HomeWork {
    private static final Logger logger = LoggerFactory.getLogger(HomeWork.class);

    public static void main(String[] args) {
// Общая часть
        var dataSource = new DataSourceH2();
        flywayMigrations(dataSource);
        var sessionManager = new SessionManagerJdbc(dataSource);
        //sessionManager.beginSession();

// Работа с пользователем
        DbExecutor<User> dbExecutor = new DbExecutorImpl<>();
        EntityClassMetaData<User> classMetaData = new EntityClassMetaDataImpl<>(User.class);
        EntitySQLMetaData sqlMetaData = new EntitySQLMetaDataImpl(classMetaData);
        JdbcMapper<User> jdbcMapperUser = new JdbcMapperImpl<>(classMetaData, sqlMetaData, dbExecutor);
        UserDao userDao = new UserDaoJdbcMapper(jdbcMapperUser, sessionManager);

// Код дальше должен остаться, т.е. userDao должен использоваться
        var dbServiceUser = new DbServiceUserImpl(userDao);
        var id = dbServiceUser.saveUser(new User(0, "dbServiceUser", 35));
        Optional<User> user = dbServiceUser.getUser(id);

        user.ifPresentOrElse(
                crUser -> logger.info("created user, name:{}, age:{}", crUser.getName(), crUser.getAge()),
                () -> logger.info("user was not created")
        );
// Работа со счетом

        EntityClassMetaData<Account> accountEntityClassMetaData = new EntityClassMetaDataImpl<>(Account.class);
        JdbcMapper<Account> accountJdbcMapper = new JdbcMapperImpl<>(accountEntityClassMetaData,
                new EntitySQLMetaDataImpl(accountEntityClassMetaData),
                new DbExecutorImpl<>()
        );
        AccountDao accountDao = new AccountDaoJdbcMapper(accountJdbcMapper,sessionManager);
        var dbServiceAccount = new DBServiceAccountImpl(accountDao);
        var no = dbServiceAccount.createAccount(new Account(10,"NewAccount",BigDecimal.TEN));
        Optional<Account> accountOptional = dbServiceAccount.getAccount(no);

        if (accountOptional.isPresent()){
            Account account = accountOptional.get();
            logger.info("created account, type:{}, rest:{}", account.getType(), account.getRest());
            account.setRest(BigDecimal.ONE);
            account.setType("NewNewAccount");
            dbServiceAccount.updateAccount(account);
            logger.info("update account, no:{} type:{}, rest:{}",account.getNo(), account.getType(), account.getRest());

        }else {
            logger.info("account was not created");
        }
    }

    private static void flywayMigrations(DataSource dataSource) {
        logger.info("db migration started...");
        var flyway = Flyway.configure()
                .dataSource(dataSource)
                .locations("classpath:/db/migration")
                .load();
        flyway.migrate();
        logger.info("db migration finished.");
        logger.info("***");
    }
}
