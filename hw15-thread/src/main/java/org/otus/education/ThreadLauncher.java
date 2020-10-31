package org.otus.education;

import org.otus.education.store.Store;
import org.otus.education.store.StoreImpl;
import org.otus.education.thread.FirstThread;
import org.otus.education.thread.SecondThread;

public class ThreadLauncher {

    public static void main(String[] args) {
        Store store = new StoreImpl(10);
        FirstThread first = new FirstThread(store);
        SecondThread second = new SecondThread(store);
        new Thread(first).start();
        new Thread(second).start();
    }
}
