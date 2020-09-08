package org.otus.education.mson.converter;

import org.otus.education.mson.converter.impl.ArrayPrimitiveConverter;
import org.otus.education.mson.converter.impl.CollectionConverter;
import org.otus.education.mson.converter.impl.DefaultObjectConverter;
import org.otus.education.mson.converter.impl.PrimitiveConverter;

import java.util.HashMap;
import java.util.Map;

public class ConverterFactoryImpl implements ConverterFactory {

    private final Map<ObjectType,Converter> typeConverterMap = new HashMap<>();

    @Override
    public Converter converterByClass(Class<?> clazz) {
        return typeConverterMap.computeIfAbsent(ObjectType.getType(clazz),this::createType);
    }

    private Converter createType(ObjectType type) {
        return switch (type) {
            case PRIMITIVE, STRING -> new PrimitiveConverter();
            case ARRAY -> new ArrayPrimitiveConverter(this);
            case COLLECTION -> new CollectionConverter(this);
        };
    }

    @Override
    public Converter objectConverter() {
        return new DefaultObjectConverter(this);
    }
}
