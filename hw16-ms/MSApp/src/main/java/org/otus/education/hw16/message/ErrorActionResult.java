package org.otus.education.hw16.message;

import ru.otus.messagesystem.client.ResultDataType;

public class ErrorActionResult extends ResultDataType {

    private String error;

    public ErrorActionResult(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
