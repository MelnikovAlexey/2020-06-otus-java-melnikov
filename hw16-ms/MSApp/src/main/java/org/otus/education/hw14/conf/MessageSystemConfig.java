package org.otus.education.hw14.conf;

import org.otus.education.hw14.data.core.service.DBServiceUser;
import org.otus.education.hw14.message.FrontEndService;
import org.otus.education.hw14.message.FrontEndServiceImpl;
import org.otus.education.hw14.message.handler.CreateUserRequestHandler;
import org.otus.education.hw14.message.handler.CreateUserResponseHandler;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.messagesystem.HandlersStore;
import ru.otus.messagesystem.HandlersStoreImpl;
import ru.otus.messagesystem.MessageSystem;
import ru.otus.messagesystem.MessageSystemImpl;
import ru.otus.messagesystem.client.CallbackRegistry;
import ru.otus.messagesystem.client.CallbackRegistryImpl;
import ru.otus.messagesystem.client.MsClient;
import ru.otus.messagesystem.client.MsClientImpl;
import ru.otus.messagesystem.message.MessageType;

@Configuration
public class MessageSystemConfig {

    private static final String DATABASE_SERVICE_CLIENT_NAME = "databaseService";
    private static final String FRONTEND_SERVICE_CLIENT_NAME = "frontendService";

    @Bean
    public MessageSystem messageSystem() {
        return new MessageSystemImpl();
    }

    @Bean
    public CallbackRegistry callbackRegistry() {
        return new CallbackRegistryImpl();
    }

    @Bean("requestHandlerDatabaseStore")
    public HandlersStore requestHandlerDatabaseStore(DBServiceUser dbService) {
        HandlersStore requestHandlerDatabaseStore = new HandlersStoreImpl();
        requestHandlerDatabaseStore.addHandler(MessageType.USER_DATA, new CreateUserRequestHandler(dbService));
        return requestHandlerDatabaseStore;
    }


    @Bean("databaseMsClient")
    public MsClient databaseMsClient(MessageSystem messageSystem,
                                     CallbackRegistry callbackRegistry,
                                     @Qualifier("requestHandlerDatabaseStore") HandlersStore handlersStore) {
        MsClient msClient = new MsClientImpl(DATABASE_SERVICE_CLIENT_NAME,
                messageSystem,
                handlersStore,
                callbackRegistry);
        messageSystem.addClient(msClient);
        return msClient;
    }

    @Bean("requestHandlerFrontendStore")
    public HandlersStore requestHandlerFrontendStore(CallbackRegistry callbackRegistry) {
        HandlersStoreImpl requestHandlerFrontendStore = new HandlersStoreImpl();
        requestHandlerFrontendStore.addHandler(MessageType.USER_DATA, new CreateUserResponseHandler(callbackRegistry));
        return requestHandlerFrontendStore;
    }

    @Bean("frontendMsClient")
    public MsClient frontendMsClient(MessageSystem messageSystem,
                                     CallbackRegistry callbackRegistry,
                                     @Qualifier("requestHandlerFrontendStore") HandlersStore handlersStore) {
        MsClient msClient = new MsClientImpl(FRONTEND_SERVICE_CLIENT_NAME,
                messageSystem,
                handlersStore,
                callbackRegistry);
        messageSystem.addClient(msClient);
        return msClient;
    }

    @Bean
    public FrontEndService frontEndService(@Qualifier("frontendMsClient") MsClient client) {
        return new FrontEndServiceImpl(client, DATABASE_SERVICE_CLIENT_NAME);
    }

}
