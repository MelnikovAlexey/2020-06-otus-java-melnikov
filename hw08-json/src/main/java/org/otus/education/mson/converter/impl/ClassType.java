package org.otus.education.mson.converter.impl;

import org.otus.education.mson.utils.ReflectionUtils;

import java.util.Collection;
import java.util.function.Predicate;

public enum ClassType {
    NUMBER(ReflectionUtils::isNumberWrapper),
    SYMBOL(ReflectionUtils::isSymbolWrapper),
    ARRAY(o -> o.getClass().isArray()),
    COLLECTION(aClass -> aClass instanceof Collection<?>),
    OTHER(aClass -> false);

    private final Predicate<Object> predicateClass;

    ClassType(Predicate<Object> predicateClass) {
        this.predicateClass = predicateClass;
    }

    public static ClassType getClassType(Object object){
        for (ClassType type:
             values()) {
            if (type.predicateClass.test(object)){
                return type;
            }

        }
        return OTHER;
    }
}
