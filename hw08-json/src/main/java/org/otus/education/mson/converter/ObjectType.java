package org.otus.education.mson.converter;

import org.otus.education.mson.exception.ClassNotSupportedException;
import org.otus.education.mson.utils.ReflectionUtils;

import java.util.Collection;
import java.util.function.Predicate;

public enum ObjectType {
    PRIMITIVE(ReflectionUtils::isPrimitiveOrWrapper),
    ARRAY(ReflectionUtils::isPrimitiveArray),
    COLLECTION(Collection.class::isAssignableFrom),
    STRING(ReflectionUtils::isString),
    OBJECT(aClass -> false);

    private final Predicate<Class<?>> classPredicate;

    ObjectType(Predicate<Class<?>> classPredicate) {
        this.classPredicate = classPredicate;
    }

    public static ObjectType getType(Class<?> aClass) {
        for (ObjectType type : values()) {
            if (type.classPredicate.test(aClass)) return type;
        }
        return OBJECT;
    }
}
