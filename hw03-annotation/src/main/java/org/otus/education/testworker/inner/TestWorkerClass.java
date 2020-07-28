package org.otus.education.testworker.inner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestWorkerClass {
    private final Class<?> clazz;
    private final List<TrinityReflectMethods> reflectMethodsList;

    public TestWorkerClass(Class<?> clazz, List<TrinityReflectMethods> reflectMethodsList) {
        this.clazz = clazz;
        this.reflectMethodsList = Collections.unmodifiableList(new ArrayList<>(reflectMethodsList));
    }

    public Class<?> getClazz() {
        return clazz;
    }

    public List<TrinityReflectMethods> getReflectMethodsList() {
        return reflectMethodsList;
    }
}
