package org.otus.education.mson.converter.impl;

import org.otus.education.mson.FieldResult;
import org.otus.education.mson.converter.Converter;
import org.otus.education.mson.converter.ConverterFactory;
import org.otus.education.mson.utils.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.otus.education.mson.utils.ReflectionUtils.getAllFields;
import static org.otus.education.mson.utils.ReflectionUtils.getFieldValue;

public class DefaultObjectConverter implements Converter {

    private final ConverterFactory factory;

    public DefaultObjectConverter(ConverterFactory factory) {
        this.factory = factory;
    }


    @Override
    public String convert(Object value) {
        if (Objects.nonNull(value)) {

            final List<Field> fieldList = getAllFields(value.getClass());
            return fieldList.stream()
                    .map(field -> {
                        return getFieldResult(value, field, factory.converterByClass(field.getType()));
                    }).filter(Objects::nonNull).map(FieldResult::toString)
                    .collect(Collectors.joining(",", "{", "}"));
        }
        return null;
    }

    private FieldResult getFieldResult(Object object, Field field, Converter converter) {
        Object value = getFieldValue(field, object);
        if (Objects.nonNull(value))
            return new FieldResult(field.getName(), converter.convert(value));
        return null;
    }


}
