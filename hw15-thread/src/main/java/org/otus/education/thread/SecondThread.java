package org.otus.education.thread;

import org.otus.education.store.Store;

public class SecondThread implements Runnable {

    private final Store store;

    public SecondThread(Store store) {
        this.store = store;
    }

    @Override
    public void run() {
        store.printOnly();
    }
}
