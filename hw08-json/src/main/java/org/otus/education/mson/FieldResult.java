package org.otus.education.mson;

public class FieldResult {
    private final String field;
    private final String value;

    public FieldResult(String field, String value) {
        this.field = field;
        this.value = value;
    }

    @Override
    public String toString() {
        return String.format("\"%s\":%s",field,value);
    }
}
