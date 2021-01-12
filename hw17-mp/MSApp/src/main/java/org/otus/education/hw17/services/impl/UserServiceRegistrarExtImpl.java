package org.otus.education.hw17.services.impl;

import org.otus.education.hw17.services.UserService;
import org.otus.education.hw17.services.UserServiceRegistrarExt;
import org.springframework.stereotype.Service;


import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class UserServiceRegistrarExtImpl implements UserServiceRegistrarExt {
    private final AtomicReference<UserService> userService = new AtomicReference<>(null);

    @Override
    public Optional<UserService> findFirst() {
        return Optional.ofNullable(userService.get());
    }

    @Override
    public void registerService(UserService service) {
        userService.set(service);
    }
}
