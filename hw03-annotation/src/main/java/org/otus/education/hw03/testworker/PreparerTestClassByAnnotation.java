package org.otus.education.hw03.testworker;

import org.otus.education.hw03.testworker.api.After;
import org.otus.education.hw03.testworker.api.Before;
import org.otus.education.hw03.testworker.api.Test;
import org.otus.education.hw03.testworker.inner.PreparerTestClassException;
import org.otus.education.hw03.testworker.inner.TestContext;
import org.otus.education.hw03.testworker.inner.TestContextCreator;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Objects;

public class PreparerTestClassByAnnotation {

    private static final String TWO_ANNOTATION_TEXT_EXCEPTION = "В тестируемом классе не может быть больше одного метода с аннотацией %s";
    private static final String EMPTY_TEXT_EXCEPTION = "В тестируемом классе не найдены тесты";

    public List<TestContext> prepare(Class<?> clazz) {
        Method[] methods = clazz.getDeclaredMethods();
        TestContextCreator methodsCreator = TestContextCreator.build(clazz);
        for (Method method : methods) {
            prepareByAnnotation(method, methodsCreator);
        }
        if (methodsCreator.isEmptyTestMethods()) {
            throw new PreparerTestClassException(EMPTY_TEXT_EXCEPTION, clazz);
        }
        return methodsCreator.createMethods();
    }

    private void prepareByAnnotation(Method method, TestContextCreator contextMethodsCreator) {
        if (Objects.nonNull(method.getDeclaredAnnotation(Test.class))) {
            contextMethodsCreator.addTestMethod(method);
        } else if (Objects.nonNull(method.getDeclaredAnnotation(After.class))) {
            if (Objects.isNull(contextMethodsCreator.getAfter())) {
                contextMethodsCreator.setAfter(method);
            } else {
                throw new PreparerTestClassException(textException(After.class), contextMethodsCreator.getClazz());
            }
        } else if (Objects.nonNull(method.getDeclaredAnnotation(Before.class))) {
            if (Objects.isNull(contextMethodsCreator.getBefore())) {
                contextMethodsCreator.setBefore(method);
            } else {
                throw new PreparerTestClassException(textException(Before.class), contextMethodsCreator.getClazz());
            }
        }
    }

    private String textException(Class<? extends Annotation> annotation) {
        return String.format(TWO_ANNOTATION_TEXT_EXCEPTION, annotation.getName());
    }
}
