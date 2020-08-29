package org.otus.education;

import org.otus.education.handler.ComplexProcessor;
import org.otus.education.listener.homework.ListenerHistoryPrinter;
import org.otus.education.processor.Processor;
import org.otus.education.processor.homework.ProcessorEvenException;
import org.otus.education.processor.homework.ProcessorFields1113Swapper;
import org.otus.education.processor.ProcessorUpperField10;

import java.util.List;

public class HomeWork {

    /*
     Реализовать to do:
       1. Добавить поля field11 - field13
       2. Сделать процессор, который поменяет местами значения field11 и field13
       3. Сделать процессор, который будет выбрасывать исключение в четную секунду
       4. Сделать Listener для ведения истории: старое сообщение - новое
     */

    /*
        Все реализации to do лежат в package homework внутри package processor и listener
     */

    public static void main(String[] args) {
        List<Processor> processors = List.of(new ProcessorFields1113Swapper(), new ProcessorEvenException(new ProcessorUpperField10()));
        //Ошибка из туду 3 выводится в консоль консумером complexProcessor-а
        ComplexProcessor complexProcessor = new ComplexProcessor(processors, ex -> System.out.println( ex.getClass().toString() + ": "+ ex.getMessage()));

        var listenerHistoryPrinter = new ListenerHistoryPrinter();
        complexProcessor.addListener(listenerHistoryPrinter);
        var message = new Message.Builder()
                .field1("field1")
                .field2("field2")
                .field3("field3")
                .field6("field6")
                .field10("field10")
                .field11("field11")
                .field13("field13")
                .build();

        //чтобы понять что мы сохраняем историю повторяем в цикле выполнение complexProcessor-а
        for (int i = 0; i < 2; i++) {
            message = complexProcessor.handle(message);
            System.out.println();
        }

        complexProcessor.removeListener(listenerHistoryPrinter);
    }
}
