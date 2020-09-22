package org.otus.education;

import org.hibernate.SessionFactory;
import org.otus.education.core.dao.UserDao;
import org.otus.education.core.model.AddressDataSet;
import org.otus.education.core.model.PhoneDataSet;
import org.otus.education.core.model.User;
import org.otus.education.core.service.DBServiceUser;
import org.otus.education.core.service.DbServiceUserImpl;
import org.otus.education.hibernate.HibernateUtils;
import org.otus.education.hibernate.dao.UserDaoHibernate;
import org.otus.education.hibernate.sessionmanager.SessionManagerHibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;


public class AppCacheLauncher {
    public static final String HIBERNATE_CFG_FILE = "hibernate.cfg.xml";
    private static final Logger logger = LoggerFactory.getLogger(AppCacheLauncher.class);

    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtils.buildSessionFactory(HIBERNATE_CFG_FILE,
                User.class, AddressDataSet.class, PhoneDataSet.class);

        SessionManagerHibernate sessionManager = new SessionManagerHibernate(sessionFactory);
        UserDao userDao = new UserDaoHibernate(sessionManager);
        DBServiceUser dbServiceUser = new DbServiceUserImpl(userDao);
        var user = new User();
        user.setName("Sherlok");
        user.setAddressDataSet(new AddressDataSet("Baker Street"));
        user.setPhoneDataSets(List.of(
                new PhoneDataSet("12345678"),
                new PhoneDataSet("87654321")
        ));

        var id = dbServiceUser.saveUser(user);
        dbServiceUser.getUser(id).ifPresentOrElse(
                crUser -> logger.info("user created, {}", crUser.toString()),
                () -> logger.info("user didn't create")
        );

    }


}
