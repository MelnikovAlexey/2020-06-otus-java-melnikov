package org.otus.education.testworker.inner;

public class PrepareTestedClassException extends RuntimeException {
    public final Class clazz;

    public PrepareTestedClassException(String message, Class clazz) {
        super(message);
        this.clazz = clazz;
    }

    public Class getClazz() {
        return clazz;
    }
}
