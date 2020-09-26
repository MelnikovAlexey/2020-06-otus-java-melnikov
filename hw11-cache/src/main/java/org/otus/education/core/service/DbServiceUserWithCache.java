package org.otus.education.core.service;

import org.otus.education.cachehw.HwCache;
import org.otus.education.core.model.User;

import java.util.Optional;

public class DbServiceUserWithCache implements DBServiceUser {

    private final DBServiceUser serviceUser;
    private final HwCache<Long, User> cache;

    public DbServiceUserWithCache(DBServiceUser serviceUser, HwCache<Long, User> cache) {
        this.serviceUser = serviceUser;
        this.cache = cache;
    }

    @Override
    public long saveUser(User user) {
        long l = serviceUser.saveUser(user);
        cache.put(l, user);
        return l;
    }

    @Override
    public Optional<User> getUser(long id) {
        Optional<User> optional = Optional.ofNullable(cache.get(id));
        if (optional.isPresent()) {
            return optional;
        }
        optional = serviceUser.getUser(id);
        optional.ifPresent(user -> cache.put(id, user));
        return optional;
    }
}
