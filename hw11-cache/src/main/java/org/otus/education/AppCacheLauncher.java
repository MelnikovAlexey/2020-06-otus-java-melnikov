package org.otus.education;

import org.hibernate.SessionFactory;
import org.otus.education.cachehw.HwCache;
import org.otus.education.cachehw.HwListener;
import org.otus.education.cachehw.MyCache;
import org.otus.education.core.dao.UserDao;
import org.otus.education.core.model.AddressDataSet;
import org.otus.education.core.model.PhoneDataSet;
import org.otus.education.core.model.User;
import org.otus.education.core.service.DBServiceUser;
import org.otus.education.core.service.DbServiceUserImpl;
import org.otus.education.core.service.DbServiceUserWithCache;
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
        HwCache<Long,User> cache = new MyCache<>(5);
        HwListener<Long,User> listener = getHwListener();
        cache.addListener(listener);
        DBServiceUser dbServiceUser = new DbServiceUserWithCache(userDao,cache);
        final long start = getStart(dbServiceUser);
        logger.info("Get from cache: {}",(System.currentTimeMillis() - start));
        cache.removeListener(listener);

        final long start2 = getStart(new DbServiceUserImpl(userDao));
        logger.info("Get from db: {}",(System.currentTimeMillis() - start2));


    }

    private static long getStart(DBServiceUser dbServiceUser) {
        var user = new User();
        user.setName("Sherlok");
        user.setAddressDataSet(new AddressDataSet("Baker Street"));
        user.setPhoneDataSets(List.of(
                new PhoneDataSet("12345678"),
                new PhoneDataSet("87654321")
        ));

        final long start = System.currentTimeMillis();

        var id = dbServiceUser.saveUser(user);
        dbServiceUser.getUser(id).ifPresentOrElse(
                crUser -> logger.info("user created, {}", crUser.toString()),
                () -> logger.info("user didn't create")
        );
        return start;
    }


    private static HwListener<Long,User> getHwListener() {
        return new HwListener<Long,User>() {
            @Override
            public void notify(Long key, User value, String action) {
                logger.info("key:{}, value:{}, action: {}", key, value.toString(), action);
            }
        };
    }


}
