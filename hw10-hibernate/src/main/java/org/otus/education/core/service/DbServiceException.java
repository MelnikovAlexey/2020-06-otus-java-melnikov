package org.otus.education.core.service;

public class DbServiceException extends RuntimeException {
    public DbServiceException(Exception e) {
        super(e);
    }
}
