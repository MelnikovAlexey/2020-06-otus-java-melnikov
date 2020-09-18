package org.otus.education.mson;

import org.otus.education.mson.converter.ConverterFactory;
import org.otus.education.mson.converter.ConverterFactoryImpl;
import org.otus.education.mson.converter.impl.ClassType;
import org.otus.education.mson.utils.ReflectionUtils;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
        if (Objects.isNull(obj)) {
            return "null";
        }
        return convert(obj);
    }

    private String convert(Object value){
      return switch (ClassType.getClassType(value)){
            case NUMBER -> value.toString();
            case SYMBOL -> String.format("\"%s\"", value.toString());
            case ARRAY ->  arrayToString(value);
            case COLLECTION -> collectionToString((Collection<?>) value);

            case OTHER ->  converterFactory.objectConverter().convert(value);
        };
        /*if (ReflectionUtils.isWrapperClass(value.getClass())) {
            if (String.class.equals(value.getClass()) || Character.class.equals(value.getClass())) {
                return String.format("\"%s\"", value.toString());
            }
            return value.toString();
        }*/

    }

    private String collectionToString(Collection<?> value) {
       return value.stream().map(v -> Objects.nonNull(v)
                ? v.toString()
                : "null").collect(Collectors.joining(",", "[", "]"));
    }

    private String arrayToString(Object value){
        List<Object> list = IntStream.range(0, Array.getLength(value))
                .mapToObj(i -> Array.get(value, i))
                .collect(Collectors.toList());
        return list.stream().map(Object::toString).collect(Collectors.joining(",", "[", "]"));
    }
}
