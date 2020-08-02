package org.otus.education.hw03.testworker.inner;

public class PreparerTestClassException extends RuntimeException {
    public final Class clazz;

    public PreparerTestClassException(String message, Class clazz) {
        super(message);
        this.clazz = clazz;
    }

    public Class getClazz() {
        return clazz;
    }
}
