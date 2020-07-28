package org.otus.education.hw03.testedclass;

import org.otus.education.testworker.api.After;
import org.otus.education.testworker.api.Before;
import org.otus.education.testworker.api.Test;

public class Test02 {
    private String text = "test";

    @Test
    public String getText() {
        System.out.println(text);
        return text;
    }

    @Before
    public void before() {
        System.out.printf("Annotation before - class exemplar: %s", this).println();
    }

    @Test
    public String getTestText() {
        text = "Hello World Test2";
        System.out.println(text);
        return text;
    }

    @Test
    public void tests() {
        throw new UnsupportedOperationException();
    }

    @After
    public void after() {
        System.out.printf("Annotation after - class exemplar: %s", this).println();
    }
}
