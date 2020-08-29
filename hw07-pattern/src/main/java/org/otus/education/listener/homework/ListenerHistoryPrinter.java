package org.otus.education.listener.homework;

import org.otus.education.Message;
import org.otus.education.listener.Listener;

import java.util.LinkedList;
import java.util.List;

public class ListenerHistoryPrinter implements Listener {

    private final List<String> logHistory;

    public ListenerHistoryPrinter() {
        this.logHistory = new LinkedList<>();
    }

    @Override
    public void onUpdated(Message oldMsg, Message newMsg) {
        var logString = String.format("oldMsg:%s, newMsg:%s", oldMsg, newMsg);
        logHistory.add(logString);
        System.out.println("Print history");
        logHistory.forEach(System.out::println);
    }
}
