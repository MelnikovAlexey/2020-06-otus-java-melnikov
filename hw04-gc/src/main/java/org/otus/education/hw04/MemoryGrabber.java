package org.otus.education.hw04;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MemoryGrabber {

    public void grabAllMemory() throws InterruptedException {
        List<Object[]> list = new ArrayList<>();
        while (true){
            int size = list.size();
            for (int i = size; i < (size+1000) * 2; i++) {
                list.add(new Object[100]);
            }
            Thread.sleep(10);
            removeEven(list);
        }
    }

    private void removeEven(List<Object[]> list) {
        int i = 0;
        for (Iterator<Object[]> it = list.iterator(); it.hasNext(); ) {
            it.next(); // Add this line in your code
            if (i % 2 == 0) {
                it.remove();
            }
            i++;
        }
    }
}
