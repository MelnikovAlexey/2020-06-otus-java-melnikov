package org.otus.education.processor.homework;

import org.otus.education.Message;
import org.otus.education.processor.Processor;

import java.time.LocalDateTime;

public class ProcessorEvenException implements Processor {

    private final Processor processor;

    public ProcessorEvenException(Processor processor) {
        this.processor = processor;
    }

    @Override
    public Message process(Message message) {
        /*try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        if (LocalDateTime.now().getSecond() % 2 == 0)
            throw new EvenException("Sorry Second is even");
        return processor.process(message);
    }
}
