package org.otus.education.hw17.services;

import org.otus.education.hw17.dto.UserDto;
import ru.otus.messagesystem.client.MessageCallback;

import java.util.List;

public interface FrontendService {

    void saveUser(UserDto userDto, MessageCallback<UserDto> dataConsumer);

    List<UserDto> findAllUsers();
}

