package org.otus.education.mson.exception;

public class MsonSerializerException extends RuntimeException {
    public MsonSerializerException(String message) {
        super(message);
    }

    public MsonSerializerException(String message, Throwable cause) {
        super(message, cause);
    }
}
