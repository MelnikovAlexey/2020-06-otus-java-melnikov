package org.otus.education.listener.homework;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

public class History {
    private final List<String> logHistory;

    public History() {
        this.logHistory = new LinkedList<>();
    }

    public void add(String log) {
        logHistory.add(log);
    }

    public void forEach(Consumer<? super String> action) {
        logHistory.forEach(action);
    }
}
