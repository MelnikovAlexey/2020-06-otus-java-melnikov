package org.otus.education.mson.converter;

public interface ConverterFactory {
    Converter converterByClass(Class<?> clazz);

    Converter objectConverter();
}
