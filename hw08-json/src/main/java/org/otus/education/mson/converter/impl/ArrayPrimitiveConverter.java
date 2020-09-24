package org.otus.education.mson.converter.impl;

import org.otus.education.mson.converter.Converter;
import org.otus.education.mson.converter.ConverterFactory;

import java.lang.reflect.Array;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ArrayPrimitiveConverter implements Converter {

    private final ConverterFactory factory;

    public ArrayPrimitiveConverter(ConverterFactory factory) {
        this.factory = factory;
    }

    @Override
    public String convert(Object value) {
        if (Objects.nonNull(value)) {
            final List<Object> list = IntStream.range(0, Array.getLength(value))
                    .mapToObj(i -> Array.get(value, i))
                    .collect(Collectors.toList());
            return list.stream()
                    .map(o -> Objects.nonNull(o)
                            ? factory.converterByClass(o.getClass()).convert(o)
                            : "null")
                    .collect(Collectors.joining(",", "[", "]"));
        }
        return "null";
    }
}
