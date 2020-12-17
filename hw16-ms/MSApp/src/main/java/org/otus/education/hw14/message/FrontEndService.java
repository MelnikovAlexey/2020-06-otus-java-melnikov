package org.otus.education.hw14.message;

import org.otus.education.hw14.data.core.model.User;
import ru.otus.messagesystem.client.MessageCallback;

public interface FrontEndService {
    void saveUser(User user, MessageCallback<User> dataConsumer);
}
