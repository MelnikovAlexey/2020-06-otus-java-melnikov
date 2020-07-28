package org.otus.education.testworker;

import org.otus.education.testworker.api.After;
import org.otus.education.testworker.api.Before;
import org.otus.education.testworker.api.Test;
import org.otus.education.testworker.inner.TestWorkerClass;
import org.otus.education.testworker.inner.TestWorkerMethodsCreator;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Objects;

public class PrepareTestedClassByAnnotation {

    private TestWorkerMethodsCreator testWorker;
    private static final String TWO_ANNOTATION_TEXT_EXCEPTION = "В тестируемом классе не может быть больше одного метода с аннотацией %s";
    private static final String EMPTY_TEXT_EXCEPTION = "В тестируемом классе не найдены тесты";

    public TestWorkerClass prepare(Class clazz) {
        Method[] methods = clazz.getDeclaredMethods();
        testWorker = TestWorkerMethodsCreator.build(clazz);
        for (Method method : methods) {
            prepareByAnnotation(method);
        }
        if (testWorker.isEmptyTestMethods()) {
            throw new PrepareTestedClassException(EMPTY_TEXT_EXCEPTION, clazz);
        }
        return testWorker.creatorClass();
    }

    private void prepareByAnnotation(Method method) {
        if (Objects.nonNull(method.getDeclaredAnnotation(Test.class))) {
            testWorker.addTestMethod(method);
        } else if (Objects.nonNull(method.getDeclaredAnnotation(After.class))) {
            if (Objects.isNull(testWorker.getAfter())) {
                testWorker.setAfter(method);
            } else {
                throw new PrepareTestedClassException(textException(After.class), testWorker.getClazz());
            }
        } else if (Objects.nonNull(method.getDeclaredAnnotation(Before.class))) {
            if (Objects.isNull(testWorker.getBefore())) {
                testWorker.setBefore(method);
            } else {
                throw new PrepareTestedClassException(textException(Before.class), testWorker.getClazz());
            }
        }
    }

    private String textException(Class<? extends Annotation> annotation) {
        return String.format(TWO_ANNOTATION_TEXT_EXCEPTION, annotation.getName());
    }
}
