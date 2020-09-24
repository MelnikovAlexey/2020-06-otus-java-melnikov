package org.otus.education;

import org.otus.education.api.Log;

public class TestAnnotationLogAllMethodAnnotate implements TestAnnotationLog {

    public TestAnnotationLogAllMethodAnnotate() {
    }

    @Log
    @Override
    public void withOneParam(String param) {

    }

    @Log
    @Override
    public void withTwoParam(String paramOne, int paramTwo) {

    }

    @Log
    @Override
    public void withTreeParam(String paramOne, int paramTwo, int paramTree) {

    }
}
