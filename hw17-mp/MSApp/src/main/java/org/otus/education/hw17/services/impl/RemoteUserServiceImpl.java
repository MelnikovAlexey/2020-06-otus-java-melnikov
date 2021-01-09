package org.otus.education.hw17.services.impl;

import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.otus.education.hw17.dto.UserDto;
import org.otus.education.hw17.protobuf.generated.Empty;
import org.otus.education.hw17.protobuf.generated.RemoteUserServiceGrpc;
import org.otus.education.hw17.protobuf.generated.UserIdQuery;
import org.otus.education.hw17.protobuf.generated.UserMessage;
import org.otus.education.hw17.services.FrontendService;
import org.springframework.stereotype.Service;



import static java.util.Objects.isNull;

@Service
@RequiredArgsConstructor
public class RemoteUserServiceImpl extends RemoteUserServiceGrpc.RemoteUserServiceImplBase {
    private final FrontendService frontendService;

    @Override
    public void getUser(UserIdQuery request, StreamObserver<UserMessage> responseObserver) {
        frontendService.getUser(request.getId(), userData -> {
            responseObserver.onNext(user2UserMessage(userData.getUserDto()));
            responseObserver.onCompleted();
        });
    }

    @Override
    public void findAllUsers(Empty request, StreamObserver<UserMessage> responseObserver) {
        frontendService.findAllUsers(userData -> {
            userData.getUserList().forEach(userDto -> responseObserver.onNext(user2UserMessage(userDto)));
            responseObserver.onCompleted();
        });

    }

    @Override
    public void saveUser(UserMessage request, StreamObserver<UserMessage> responseObserver) {
        val userDto = new UserDto(request.getId(), request.getName(), request.getLogin(), request.getPassword());
        frontendService.saveUser(userDto, userData -> {
            responseObserver.onNext(user2UserMessage(userData.getUserDto()));
            responseObserver.onCompleted();
        });
    }

    private UserMessage user2UserMessage(UserDto user) {
        if (isNull(user))
            return null;
        return UserMessage.newBuilder()
                .setId(user.getUserId())
                .setName(user.getName())
                .setLogin(user.getLogin())
                .setPassword(user.getPassword())
                .build();
    }
}
