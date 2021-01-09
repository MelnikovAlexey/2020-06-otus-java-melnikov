package org.otus.education.hw17.front.config;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.otus.education.hw17.front.protobuf.generated.RemoteUserServiceGrpc;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RemoteConfig {

    @Value("${app.grpcPort}")
    private int grpcPort;

    @Bean
    public ManagedChannel grpcChannel(){
        return ManagedChannelBuilder.forAddress("localhost", grpcPort)
                .usePlaintext()
                .build();
    }

    @Bean
    public RemoteUserServiceGrpc.RemoteUserServiceBlockingStub serviceStub(ManagedChannel channel){
        return  RemoteUserServiceGrpc.newBlockingStub(channel);
    }
}
