package org.otus.education.mson.converter.impl;

import org.otus.education.mson.converter.Converter;

import java.util.Objects;

public class PrimitiveConverter implements Converter {
    @Override
    public String convert(Object value) {
        if (Objects.isNull(value)) {
            return "null";
        }
        return value.getClass().equals(Character.class) || value.getClass().equals(String.class)
                ? String.format("\"%s\"", value.toString())
                : value.toString();
    }
}
