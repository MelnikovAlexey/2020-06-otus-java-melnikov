package org.otus.education.hw16.message;

import lombok.extern.slf4j.Slf4j;
import org.otus.education.hw16.data.core.model.User;
import ru.otus.messagesystem.client.MessageCallback;
import ru.otus.messagesystem.client.MsClient;
import ru.otus.messagesystem.message.Message;

@Slf4j
public class FrontEndServiceImpl implements FrontEndService {

    private final MsClient msClient;
    private final  String dbServiceClientName;

    public FrontEndServiceImpl(MsClient msClient, String dbServiceClientName) {
        this.msClient = msClient;
        this.dbServiceClientName = dbServiceClientName;
    }

    @Override
    public void saveUser(User user, MessageCallback<User> userMessageCallback, MessageCallback<ErrorActionResult> errorMessageCallback) {

    }

}
