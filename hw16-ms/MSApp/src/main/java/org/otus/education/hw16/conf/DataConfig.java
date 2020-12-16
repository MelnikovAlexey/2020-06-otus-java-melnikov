package org.otus.education.hw16.conf;

import org.flywaydb.core.Flyway;
import org.hibernate.SessionFactory;
import org.otus.education.hw16.data.core.model.User;
import org.otus.education.hw16.data.hibernate.HibernateUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.sql.DataSource;

@Configuration
@ComponentScan
public class DataConfig {

    @Bean(initMethod = "migrate")
    public Flyway flyway(DataSource dataSource) {
        return Flyway.configure()
                .dataSource(dataSource)
                .locations("/db/migration")
                .load();
    }

    @Bean
    @DependsOn({"flyway"})
    public SessionFactory sessionFactory(DataSource dataSource) {
        return HibernateUtils.buildSessionFactory("/hibernate.cfg.xml", dataSource, User.class);
    }
}
