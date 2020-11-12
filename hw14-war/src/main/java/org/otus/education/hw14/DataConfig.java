package org.otus.education.hw14;

import org.flywaydb.core.Flyway;
import org.h2.jdbcx.JdbcDataSource;
import org.hibernate.SessionFactory;
import org.otus.education.hw14.data.core.model.User;
import org.otus.education.hw14.data.hibernate.HibernateUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.sql.DataSource;

@Configuration
@ComponentScan
public class DataConfig {
    @Value("${database.url}")
    private String dbUrl;

    @Value("${database.user}")
    private String dbUser;

    @Value("${database.password}")
    private String dbPassword;

    @Value("${flyway.scripts}")
    private String flywayScripts;

    @Value("${hibernate.config}")
    private String hibernateConfig;

    @Bean
    public DataSource dataSource() {
        var dataSource = new JdbcDataSource();
        dataSource.setUser(dbUser);
        dataSource.setPassword(dbPassword);
        dataSource.setURL(dbUrl);
        return dataSource;
    }

    @Bean(initMethod = "migrate")
    public Flyway flyway(DataSource dataSource) {
        return Flyway.configure()
                .dataSource(dataSource)
                .locations(flywayScripts)
                .load();
    }

    @Bean
    @DependsOn({"flyway"})
    public SessionFactory sessionFactory(DataSource dataSource) {
        return HibernateUtils.buildSessionFactory(hibernateConfig, dataSource, User.class);
    }
}
