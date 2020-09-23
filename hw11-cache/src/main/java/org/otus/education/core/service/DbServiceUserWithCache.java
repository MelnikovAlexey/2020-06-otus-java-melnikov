package org.otus.education.core.service;

import org.otus.education.cachehw.HwCache;
import org.otus.education.core.dao.UserDao;
import org.otus.education.core.model.User;

import java.util.Optional;

public class DbServiceUserWithCache extends DbServiceUserImpl {

    private  HwCache<Long,User> cache;

    private DbServiceUserWithCache(UserDao userDao) {
        super(userDao);
    }

    public DbServiceUserWithCache(UserDao userDao, HwCache<Long, User> cache) {
        super(userDao);
        this.cache = cache;
    }

    @Override
    public long saveUser(User user) {
        long l = super.saveUser(user);
        cache.put(l,user);
        return l;
    }

    @Override
    public Optional<User> getUser(long id) {
        Optional<User> optional = Optional.ofNullable(cache.get(id));
        if (optional.isPresent()){
            return optional;
        }
        return super.getUser(id);
    }
}
