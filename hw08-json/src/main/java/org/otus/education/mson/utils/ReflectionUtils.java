package org.otus.education.mson.utils;

import org.otus.education.mson.exception.FieldAccessException;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;
import java.util.stream.Collectors;

public class ReflectionUtils {

    private static final Set<Class<?>> WRAPPER = Set.of(Integer.class, Long.class,
            Byte.class, Short.class, Character.class, Float.class, Boolean.class, Double.class);
    private static final Set<Class<?>> NUMBER = Set.of(Integer.class, Long.class,
            Byte.class, Short.class, Float.class, Boolean.class, Double.class);

    public static boolean isPrimitiveOrWrapper(Class<?> clazz) {
        return clazz.isPrimitive() || isWrapper(clazz);
    }

    public static boolean isPrimitiveArray(Class<?> clazz) {
        return Objects.nonNull(clazz) && clazz.isArray()
                && ((clazz.getComponentType().isPrimitive()) || isPrimitiveArray(clazz.getComponentType()));
    }

    public static boolean isWrapperClass(Class<?> clazz){
        return isWrapper(clazz) || isString(clazz);
    }

    public static boolean isString(Class<?> clazz) {
        return clazz.equals(String.class);
    }

    public static Object getFieldValue(Field field, Object object) {
        try {
            field.setAccessible(true);
            return field.get(object);
        } catch (IllegalAccessException e) {
            throw new FieldAccessException("Field access error", e);
        }
    }

    public static boolean isNumberWrapper(Object value){
        return NUMBER.contains(value.getClass());
    }

    public static boolean isSymbolWrapper(Object value){
      return String.class.equals(value.getClass()) || Character.class.equals(value.getClass());
    }

    public static List<Field> getAllFields(Class<?> clazz) {
        if (Objects.nonNull(clazz)) {
            List<Field> list =Arrays.stream(clazz.getDeclaredFields()).filter(ReflectionUtils::isSerializable).collect(Collectors.toList());
            list.addAll(getAllFields(clazz.getSuperclass()));
            return list;
        }

        return Collections.emptyList();
    }
    private static boolean isSerializable(Field field) {
        return !Modifier.isStatic(field.getModifiers()) && !Modifier.isTransient(field.getModifiers());
    }
    private static boolean isWrapper(Class<?> clazz){
        return WRAPPER.contains(clazz);
    }
}
