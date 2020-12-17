package org.otus.education.hw16.message;

import org.otus.education.hw16.data.core.model.User;
import ru.otus.messagesystem.client.MessageCallback;

public interface FrontEndService {
    void saveUser(User user, MessageCallback<User> dataConsumer);
}
