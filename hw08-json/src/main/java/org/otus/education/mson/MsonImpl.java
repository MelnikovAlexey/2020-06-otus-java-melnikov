package org.otus.education.mson;

import org.otus.education.mson.converter.ConverterFactory;
import org.otus.education.mson.converter.ConverterFactoryImpl;

import java.util.Objects;

public class MsonImpl implements Mson {

    private final ConverterFactory converterFactory;

    public MsonImpl(ConverterFactory converterFactory) {
        this.converterFactory = converterFactory;
    }

    public MsonImpl() {
        this(new ConverterFactoryImpl());
    }

    @Override
    public String toJson(Object obj) {
        Objects.requireNonNull(obj, "Object should not be null");
        return converterFactory.objectConverter().convert(obj);
    }
}
