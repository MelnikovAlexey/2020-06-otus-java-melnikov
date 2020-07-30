package org.otus.education.testworker.inner;

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
