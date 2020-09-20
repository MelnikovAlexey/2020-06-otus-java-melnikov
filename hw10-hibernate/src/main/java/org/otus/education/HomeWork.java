package org.otus.education;

import org.flywaydb.core.Flyway;
import org.otus.education.core.model.User;
import org.otus.education.h2.DataSourceH2;
import org.otus.education.jdbc.DbExecutor;
import org.otus.education.jdbc.DbExecutorImpl;
import org.otus.education.jdbc.sessionmanager.SessionManagerJdbc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;


public class HomeWork {
    private static final Logger logger = LoggerFactory.getLogger(HomeWork.class);

    public static void main(String[] args) {
// Общая часть
        var dataSource = new DataSourceH2();
        flywayMigrations(dataSource);
        var sessionManager = new SessionManagerJdbc(dataSource);

// Работа с пользователем
        DbExecutor<User> dbExecutor = new DbExecutorImpl<>();
       /* EntityClassMetaData<User> classMetaData = new EntityClassMetaDataImpl<>(User.class);
        EntitySQLMetaData sqlMetaData = new EntitySQLMetaDataImpl(classMetaData);
        JdbcMapper<User> jdbcMapperUser = new JdbcMapperImpl<>(classMetaData, sqlMetaData, dbExecutor);*/
     //   UserDao userDao = new UserDaoJdbcMapper(jdbcMapperUser, sessionManager);

// Код дальше должен остаться, т.е. userDao должен использоваться
        var dbServiceUser = "null";// new DbServiceUserImpl(userDao);


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
