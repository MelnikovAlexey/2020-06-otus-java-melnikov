package org.otus.education.hw03.testworker.inner;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;
import java.util.Optional;

public class TestContext {
    private final Method before;
    private final Method after;
    private final Method test;
    private final Class<?> clazz;
    private Object instance;

    private TestContext(Class<?> clazz, Method before, Method test, Method after) {
        if (Objects.isNull(test)) {
            throw new NullPointerException("Нет тестируемого метода");
        }
        this.before = before;
        this.after = after;
        this.test = test;
        this.clazz = clazz;
    }

    public static TestContext build(Class<?> clazz, Method before, Method test, Method after){
        return new TestContext( clazz, before, test, after);
    }

    public Optional<Throwable> createTestInstance() {
        try {
            instance = clazz.getDeclaredConstructor().newInstance();
        } catch (Throwable throwable) {
            return Optional.of(throwable);
        }
        return Optional.empty();
    }

    public String getMethodName() {
        return test.getName();
    }

    public Optional<Throwable> executeBeforeMethod() {
        return invokeMethod(before);
    }

    public Optional<Throwable> executeAfterMethod() {
        return invokeMethod(after);
    }

    public Optional<Throwable> executeTestMethod() {
        return invokeMethod(test);
    }

    public boolean isAfter() {
        return Objects.nonNull(after);
    }

    public boolean isBefore() {
        return Objects.nonNull(before);
    }

    private Optional<Throwable> invokeMethod(Method method) {
        if (Objects.nonNull(method)) {
            try {
                method.invoke(instance);
            } catch (InvocationTargetException e) {
                return Optional.of(e.getCause());
            } catch (Throwable t) {
                return Optional.of(t);
            }
        }
        return Optional.empty();
    }

}
