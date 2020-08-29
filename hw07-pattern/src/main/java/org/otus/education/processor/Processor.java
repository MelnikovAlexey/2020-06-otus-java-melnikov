package org.otus.education.processor;

import org.otus.education.Message;

public interface Processor {

    Message process(Message message);

}
