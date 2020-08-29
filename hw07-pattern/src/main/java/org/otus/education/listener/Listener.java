package org.otus.education.listener;

import org.otus.education.Message;

public interface Listener {

    void onUpdated(Message oldMsg, Message newMsg);

}
