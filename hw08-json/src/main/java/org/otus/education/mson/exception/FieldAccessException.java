package org.otus.education.mson.exception;

public class FieldAccessException extends MsonSerializerException {

    public FieldAccessException(String message, Throwable e) {
        super(message, e);
    }
}
