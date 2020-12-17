package org.otus.education.hw14.message.handler;

import lombok.extern.slf4j.Slf4j;
import org.otus.education.hw14.data.core.model.User;
import org.otus.education.hw14.data.core.service.DBServiceUser;
import ru.otus.messagesystem.RequestHandler;
import ru.otus.messagesystem.message.Message;
import ru.otus.messagesystem.message.MessageBuilder;
import ru.otus.messagesystem.message.MessageHelper;

import java.util.Optional;

@Slf4j
public class CreateUserRequestHandler implements RequestHandler<User> {

    private final DBServiceUser dbServiceUser;

    public CreateUserRequestHandler(DBServiceUser dbServiceUser) {
        this.dbServiceUser = dbServiceUser;
    }

    @Override
    public Optional<Message> handle(Message msg) {
        User user = MessageHelper.getPayload(msg);
        try {
            dbServiceUser.saveUser(user);
        } catch (Exception e) {
            log.error(e.getMessage());
            return Optional.empty();
        }

        return Optional.of(MessageBuilder.buildReplyMessage(msg, user));
    }
}
