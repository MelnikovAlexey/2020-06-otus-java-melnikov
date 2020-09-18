package org.otus.education.mson.converter;

import org.otus.education.mson.converter.impl.*;

import java.util.HashMap;
import java.util.Map;

public class ConverterFactoryImpl implements ConverterFactory {

    private final Map<ObjectType, Converter> typeConverterMap = new HashMap<>();

    @Override
    public Converter converterByClass(Class<?> clazz) {
        return typeConverterMap.computeIfAbsent(ObjectType.getType(clazz), this::createType);
    }

    private Converter createType(ObjectType type) {
        return switch (type) {
            case PRIMITIVE, STRING -> new PrimitiveConverter();
            case ARRAY -> new ArrayPrimitiveConverter(this);
            case COLLECTION -> new CollectionConverter(this);
            case OBJECT -> new ObjectConverter(this);
        };
    }

    @Override
    public Converter objectConverter() {
        return new DefaultObjectConverter(this);
    }
}
