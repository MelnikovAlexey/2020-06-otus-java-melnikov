package org.otus.education.testworker;

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
