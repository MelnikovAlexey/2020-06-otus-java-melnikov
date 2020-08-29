package org.otus.education.processor.homework;

import org.otus.education.Message;
import org.otus.education.processor.Processor;

public class ProcessorFields1113Swapper implements Processor {
    @Override
    public Message process(Message message) {
        String field11 = message.getField11();
        String field13 = message.getField13();
        return message.toBuilder().field11(field13).field13(field11).build();
    }
}
