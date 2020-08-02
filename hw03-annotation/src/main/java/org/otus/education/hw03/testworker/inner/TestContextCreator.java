package org.otus.education.hw03.testworker.inner;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TestContextCreator {
    private final Class<?> clazz;
    private Method before;
    private Method after;
    private final List<Method> tests = new ArrayList<>();

    private TestContextCreator(Class<?> clazz) {
        this.clazz = clazz;
    }

    public static TestContextCreator build(Class<?> clazz) {
        return new TestContextCreator(clazz);
    }

    public Class<?> getClazz() {
        return clazz;
    }

    public Method getBefore() {
        return before;
    }

    public void setBefore(Method before) {
        this.before = before;
    }

    public Method getAfter() {
        return after;
    }

    public void setAfter(Method after) {
        this.after = after;
    }

    public void addTestMethod(Method test) {
        tests.add(test);
    }

    public boolean isEmptyTestMethods() {
        return tests.isEmpty();
    }

    public List<TestContext> createMethods() {
        return tests.stream().map(f -> TestContext.build(clazz, before, f, after)).collect(Collectors.toList());
    }
}
