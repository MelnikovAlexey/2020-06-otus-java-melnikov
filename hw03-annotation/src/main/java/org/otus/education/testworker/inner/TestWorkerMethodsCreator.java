package org.otus.education.testworker.inner;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TestWorkerMethodsCreator {
    private final Class clazz;
    private Method before;
    private Method after;
    private List<Method> tests = new ArrayList<>();

    private TestWorkerMethodsCreator(Class clazz) {
        this.clazz = clazz;
    }

    public static TestWorkerMethodsCreator build(Class clazz) {
        return new TestWorkerMethodsCreator(clazz);
    }

    public TestWorkerClass creatorClass() {
        return new TestWorkerClass(clazz, createTrinityMethods());
    }

    public Class getClazz() {
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

    public List<TrinityReflectMethods> createTrinityMethods() {
        return tests.stream().map(f -> new TrinityReflectMethods(clazz, before, f, after)).collect(Collectors.toList());
    }
}
