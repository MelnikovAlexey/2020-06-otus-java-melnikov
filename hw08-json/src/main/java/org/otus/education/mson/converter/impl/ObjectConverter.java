package org.otus.education.mson.converter.impl;

import org.otus.education.mson.FieldResult;
import org.otus.education.mson.converter.Converter;
import org.otus.education.mson.converter.ConverterFactory;
import org.otus.education.mson.utils.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;

public class ObjectConverter implements Converter {
    private final ConverterFactory converterFactory;

    public ObjectConverter(ConverterFactory converterFactory) {
        this.converterFactory = converterFactory;
    }

    @Override
    public String convert(Object object) {
        if (nonNull(object)) {
            final List<Field> fields = ReflectionUtils.getAllFields(object.getClass());
            final String collect = fields.stream()
                    .map(field -> getFieldConvertResult(object, field, converterFactory.converterByClass(field.getType())))
                    .filter(Objects::nonNull)
                    .map(convertResult -> String.format("\"%s\":%s", convertResult.getField(), convertResult.getValue()))
                    .collect(Collectors.joining(","));
            return String.format("{%s}", collect);
        }
        return null;
    }

    private FieldResult getFieldConvertResult(Object object,
                                              Field field,
                                              Converter converter) {
        final Object fieldValue = ReflectionUtils.getFieldValue(field, object);
        if (nonNull(fieldValue))
            return new FieldResult(field.getName(), converter.convert(fieldValue));
        return null;
    }

}
