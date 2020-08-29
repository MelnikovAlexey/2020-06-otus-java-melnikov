package org.otus.education.processor;

import org.otus.education.Message;

public interface Processor {

    Message process(Message message);

    //todo: 2. Сделать процессор, который поменяет местами значения field11 и field13
}
