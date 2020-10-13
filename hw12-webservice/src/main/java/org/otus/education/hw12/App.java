package org.otus.education.hw12;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.flywaydb.core.Flyway;
import org.h2.jdbcx.JdbcDataSource;
import org.otus.education.hw12.data.core.model.User;
import org.otus.education.hw12.data.core.service.DbServiceUserImpl;
import org.otus.education.hw12.data.hibernate.HibernateUtils;
import org.otus.education.hw12.data.hibernate.dao.UserDaoHibernate;
import org.otus.education.hw12.data.hibernate.sessionmanager.SessionManagerHibernate;
import org.otus.education.hw12.web.server.UsersWebServerWithFilterBasedSecurity;
import org.otus.education.hw12.web.services.TemplateProcessorImpl;
import org.otus.education.hw12.web.services.UserAuthService;
import org.otus.education.hw12.web.services.UserAuthServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;


public class App {

    private static final Logger logger = LoggerFactory.getLogger(App.class);


    private static final String HIBERNATE_CONFIG = "hibernate.cfg.xml";
    private static final int WEB_SERVER_PORT = 8080;
    private static final String TEMPLATE_DIRECTORY = "/templates/";
    private static final String DB_USER = "sa";
    private static final String DB_PASS = "sa";
    private static final String DB_URI = "jdbc:h2:mem:OtusExamplesDB;DB_CLOSE_DELAY=-1";

    public static void main(String[] args) throws Exception {
        DataSource dataSource = initDataSource();
        flywayMigrations(dataSource);
        var sessionFactory = HibernateUtils.buildSessionFactory(HIBERNATE_CONFIG, dataSource, User.class);
        try (var sessionManager = new SessionManagerHibernate(sessionFactory)) {

            var dbServiceUser = new DbServiceUserImpl(new UserDaoHibernate(sessionManager));

            Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting().create();
            var templateProcessor = new TemplateProcessorImpl(TEMPLATE_DIRECTORY);
            UserAuthService userAuthService = new UserAuthServiceImpl(dbServiceUser);

            UsersWebServerWithFilterBasedSecurity usersWebServer = new UsersWebServerWithFilterBasedSecurity(WEB_SERVER_PORT,
                    userAuthService, gson, dbServiceUser, templateProcessor);

            usersWebServer.start();
            usersWebServer.join();
        }
    }

    private static DataSource initDataSource() {
        var dataSource = new JdbcDataSource();
        dataSource.setURL(DB_URI);
        dataSource.setPassword(DB_PASS);
        dataSource.setUser(DB_USER);
        return dataSource;
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
