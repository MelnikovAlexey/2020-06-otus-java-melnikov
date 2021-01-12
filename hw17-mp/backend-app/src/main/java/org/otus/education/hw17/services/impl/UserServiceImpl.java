package org.otus.education.hw17.services.impl;


import org.otus.education.hw17.domain.User;
import org.otus.education.hw17.dto.UserDto;
import org.otus.education.hw17.services.DBServiceUser;
import org.otus.education.hw17.services.UserService;


import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService, Serializable {
    private final transient DBServiceUser serviceUser;

    public UserServiceImpl(DBServiceUser serviceUser) {
        this.serviceUser = serviceUser;
    }

    @Override
    public long save(UserDto user) {
        return serviceUser.save(User.fromDto(user));
    }

    @Override
    public List<UserDto> getAll() {
        return serviceUser.getAll().stream().map(UserDto::fromUser).collect(Collectors.toList());
    }

}
