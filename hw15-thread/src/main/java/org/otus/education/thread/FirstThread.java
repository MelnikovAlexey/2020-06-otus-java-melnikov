package org.otus.education.thread;

import org.otus.education.store.Store;

public class FirstThread implements Runnable {

    private final Store store;

    public FirstThread(Store store) {
        this.store = store;
    }

    @Override
    public void run() {
        store.printAndChange();
    }
}
