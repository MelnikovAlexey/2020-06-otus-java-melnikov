package org.otus.education.hw17.services;

import com.google.common.collect.Lists;
import org.otus.education.hw17.dto.UserDto;
import org.otus.education.hw17.protobuf.generated.Empty;
import org.otus.education.hw17.protobuf.generated.RemoteUserServiceGrpc;
import org.otus.education.hw17.protobuf.generated.UserIdQuery;
import org.otus.education.hw17.protobuf.generated.UserMessage;
import org.springframework.stereotype.Service;
import ru.otus.messagesystem.client.MessageCallback;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FrontendServiceImpl implements FrontendService {

    private final RemoteUserServiceGrpc.RemoteUserServiceBlockingStub stub;

    public FrontendServiceImpl(RemoteUserServiceGrpc.RemoteUserServiceBlockingStub stub) {
        this.stub = stub;
    }

    @Override
    public void saveUser(UserDto userDto, MessageCallback<UserDto> dataConsumer) {
        UserMessage message = stub.saveUser(user2UserMessage(userDto));
        dataConsumer.accept(message2Dto(message));
    }

    @Override
    public List<UserDto> findAllUsers() {
        final var users = stub.findAllUsers(Empty.newBuilder().build());
        return Lists.newArrayList(users).stream()
                .map(this::message2Dto)
                .collect(Collectors.toList());
    }


    private UserDto message2Dto(UserMessage message) {
        return new UserDto(message.getId(), message.getName(), message.getLogin(), message.getPassword());
    }

    private UserMessage user2UserMessage(UserDto user) {
        return UserMessage.newBuilder()
                .setId(user.getUserId())
                .setName(user.getName())
                .setLogin(user.getLogin())
                .setPassword(user.getPassword())
                .build();
    }
}
