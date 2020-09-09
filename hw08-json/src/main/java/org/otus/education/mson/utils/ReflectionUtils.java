package org.otus.education.mson.utils;

import org.otus.education.mson.exception.FieldAccessException;

import java.lang.reflect.Field;
import java.util.*;

public class ReflectionUtils {

    private static final Set<Class<?>> WRAPPER = Set.of(Integer.class, Long.class,
            Byte.class, Short.class, Character.class, Float.class, Boolean.class, Double.class);

    public static boolean isPrimitiveOrWrapper(Class<?> clazz) {
        return clazz.isPrimitive() || WRAPPER.contains(clazz);
    }

    public static boolean isPrimitiveArray(Class<?> clazz) {
        return Objects.nonNull(clazz) && clazz.isArray()
                && ((clazz.getComponentType().isPrimitive()) || isPrimitiveArray(clazz.getComponentType()));
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

    public static List<Field> getAllFields(Class<?> clazz) {
        if (Objects.nonNull(clazz)) {
            List<Field> list = new ArrayList<>(Arrays.asList(clazz.getDeclaredFields()));
            list.addAll(getAllFields(clazz.getSuperclass()));
            return list;
        }
        return Collections.emptyList();
    }
}
