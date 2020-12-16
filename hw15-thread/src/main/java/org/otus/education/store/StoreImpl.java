package org.otus.education.store;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicBoolean;

@Slf4j
public class StoreImpl implements Store {
  //  private static final Logger logger = LoggerFactory.getLogger(StoreImpl.class);
    private final AtomicBoolean stopper = new AtomicBoolean(false);
    private final AtomicBoolean changer = new AtomicBoolean(true);
    private int number = 0;
    private int limit;

    public StoreImpl(int limit) {
        this.limit = limit;
    }

    @Override
    public synchronized void printAndChange() {
        for (int j = 0; j < 2; j++) {
            for (int i = 0; i < limit; i++) {
                while (stopper.get()) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                    }
                }
                if (changer.get()) {
                    number++;
                } else {
                    number--;
                }
                log.info("First print: {}", number);
                stopper.set(true);
                sleep();
                notifyAll();
            }
            changer.set(false);
            limit--;
        }
    }

    @Override
    public synchronized void printOnly() {
        for (int j = 0; j < 2; j++) {
            for (int i = 0; i < limit; i++) {
                while (!stopper.get()) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                    }
                }
                log.info("Second print: {}", number);
                stopper.set(false);
                sleep();
                notify();
            }
        }
    }

    private void sleep() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
