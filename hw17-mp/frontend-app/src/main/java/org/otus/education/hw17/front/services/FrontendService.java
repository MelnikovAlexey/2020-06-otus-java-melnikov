package org.otus.education.hw17.front.services;

import org.otus.education.hw17.front.dto.UserDto;
import ru.otus.messagesystem.client.MessageCallback;

import java.util.List;

public interface FrontendService {
    void getUser(long userId, MessageCallback<UserDto> dataConsumer);

    void saveUser(UserDto userDto, MessageCallback<UserDto> dataConsumer);

    List<UserDto> findAllUsers();
}

