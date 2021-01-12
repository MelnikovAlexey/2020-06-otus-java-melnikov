package org.otus.education.hw17.services;

import java.util.Optional;

public interface UserServiceRegistrarExt extends UserServiceRegistrar {
    Optional<UserService> findFirst();
}
