package org.otus.education;

import org.otus.education.api.Log;

public class TestAnnotationLogWithOneAnnotation implements TestAnnotationLog {

    @Log
    @Override
    public void withOneParam(String param) {

    }

    @Override
    public void withTwoParam(String paramOne, int paramTwo) {

    }

    @Override
    public void withTreeParam(String paramOne, int paramTwo, int paramTree) {

    }
}
