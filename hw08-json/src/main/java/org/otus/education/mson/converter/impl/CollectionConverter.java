package org.otus.education.mson.converter.impl;

import org.otus.education.mson.converter.Converter;
import org.otus.education.mson.converter.ConverterFactory;

import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;

public class CollectionConverter implements Converter {

    private final ConverterFactory factory;

    public CollectionConverter(ConverterFactory factory) {
        this.factory = factory;
    }

    @Override
    public String convert(Object value) {
        if (Objects.nonNull(value)) {
            return ((Collection<?>) value).stream()
                    .map(v -> Objects.nonNull(v)
                            ? factory.converterByClass(v.getClass()).convert(v)
                            : "null")
                    .collect(Collectors.joining(",", "[", "]"));
        }
        return null;
    }
}
