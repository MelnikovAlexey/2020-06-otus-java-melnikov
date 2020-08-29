package org.otus.education.processor;

import org.otus.education.Message;

public class LoggerProcessor implements Processor {
    //todo: 3. Сделать процессор, который будет выбрасывать исключение в четную секунду

    private final Processor processor;

    public LoggerProcessor(Processor processor) {
        this.processor = processor;
    }

    @Override
    public Message process(Message message) {
        System.out.println("log processing message:" + message);
        return processor.process(message);
    }
}
