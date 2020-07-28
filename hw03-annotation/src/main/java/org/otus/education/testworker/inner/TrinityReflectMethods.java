package org.otus.education.testworker.inner;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;
import java.util.Optional;

public class TrinityReflectMethods {
    private final Method before;
    private final Method after;
    private final Method test;
    private final Class clazz;
    private Object instance;

    public TrinityReflectMethods(Class clazz, Method before, Method test, Method after) {
        if (Objects.isNull(test)) {
            throw new NullPointerException("Нет тестируемого метода");
        }
        this.before = before;
        this.after = after;
        this.test = test;
        this.clazz = clazz;
    }

    public Optional<Throwable> init() {
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

    public Optional<Throwable> before() {
        return invokeMethod(before);
    }

    public Optional<Throwable> after() {
        return invokeMethod(after);
    }

    public Optional<Throwable> test() {
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
