package org.otus.education.hw17.services;


import org.otus.education.hw17.dto.UserDto;
import org.otus.education.hw17.dto.UserDtoResultType;
import ru.otus.messagesystem.client.MessageCallback;

public interface FrontendService {

    void saveUser(UserDto userDto, MessageCallback<UserDtoResultType> dataConsumer);

    void findAllUsers(MessageCallback<UserDtoResultType> dataConsumer);

}

