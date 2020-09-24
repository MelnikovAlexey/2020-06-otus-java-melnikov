package org.otus.education;

public class DemoApp {

    public static void main(String[] args) {
        TestAnnotationLog test1 = (TestAnnotationLog) LogAnnotationCreator.createTestLogging(TestAnnotationLogAllMethodAnnotate.class);
        test1.withOneParam("1");
        test1.withTwoParam("thisOne",2);
        test1.withTreeParam("One",2,3);


        TestAnnotationLog test2 = (TestAnnotationLog) LogAnnotationCreator.createTestLogging(TestAnnotationLogWithOneAnnotation.class);
        test2.withOneParam("This with annotation @Log");
        test2.withTwoParam("No annotation @Log",2);
        test2.withTreeParam("No @Log",2,3);
    }


}
