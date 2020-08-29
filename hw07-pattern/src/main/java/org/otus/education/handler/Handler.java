package org.otus.education.handler;

import org.otus.education.Message;
import org.otus.education.listener.Listener;

public interface Handler {
    Message handle(Message msg);

    void addListener(Listener listener);
    void removeListener(Listener listener);
}
