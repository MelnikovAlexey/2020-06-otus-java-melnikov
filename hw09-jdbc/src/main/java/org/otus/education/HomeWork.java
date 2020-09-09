package org.otus.education;

import org.flywaydb.core.Flyway;
import org.otus.education.core.dao.UserDao;
import org.otus.education.core.model.User;
import org.otus.education.core.service.DbServiceUserImpl;
import org.otus.education.h2.DataSourceH2;
import org.otus.education.jdbc.DbExecutorImpl;
import org.otus.education.jdbc.mapper.JdbcMapper;
import org.otus.education.jdbc.sessionmanager.SessionManagerJdbc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import javax.sql.DataSource;
import java.util.Optional;


public class HomeWork {
    private static final Logger logger = LoggerFactory.getLogger(HomeWork.class);

    public static void main(String[] args) {
// Общая часть
        var dataSource = new DataSourceH2();
        flywayMigrations(dataSource);
        var sessionManager = new SessionManagerJdbc(dataSource);

// Работа с пользователем
        DbExecutorImpl<User> dbExecutor = new DbExecutorImpl<>();
        JdbcMapper<User> jdbcMapperUser = null; //
        UserDao userDao = null; // = new UserDaoJdbcMapper(sessionManager, dbExecutor);

// Код дальше должен остаться, т.е. userDao должен использоваться
        var dbServiceUser = new DbServiceUserImpl(userDao);
        var id = dbServiceUser.saveUser(new User(0, "dbServiceUser"));
        Optional<User> user = dbServiceUser.getUser(id);

        user.ifPresentOrElse(
                crUser -> logger.info("created user, name:{}", crUser.getName()),
                () -> logger.info("user was not created")
        );
// Работа со счетом


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
