package org.otus.education.listener.homework;

import org.otus.education.Message;
import org.otus.education.listener.Listener;

import java.util.LinkedList;
import java.util.List;

public class ListenerHistoryPrinter implements Listener {

    private final History logHistory;

    public ListenerHistoryPrinter(History history) {
        this.logHistory = history;
    }

    @Override
    public void onUpdated(Message oldMsg, Message newMsg) {
        var logString = String.format("oldMsg:%s, newMsg:%s", oldMsg, newMsg);
        logHistory.add(logString);
    }
}
