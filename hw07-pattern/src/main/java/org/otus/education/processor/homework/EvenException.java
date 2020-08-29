package org.otus.education.processor.homework;

public class EvenException extends RuntimeException {
    public EvenException() {
        super();
    }

    public EvenException(String message) {
        super(message);
    }

    public EvenException(String message, Throwable cause) {
        super(message, cause);
    }

    public EvenException(Throwable cause) {
        super(cause);
    }

    protected EvenException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
