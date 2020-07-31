package org.otus.education.hw03.testworker;

import org.otus.education.hw03.testworker.inner.ResultsTestInfo;
import org.otus.education.hw03.testworker.inner.TestContext;
import org.otus.education.hw03.testworker.inner.TestDetails;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestClassLauncher {

    private final PreparerTestClassByAnnotation prepareTestedClass;

    private TestClassLauncher(PreparerTestClassByAnnotation prepareTestedClass) {
        this.prepareTestedClass = prepareTestedClass;
    }

    public static TestClassLauncher build(PreparerTestClassByAnnotation prepareTestedClass) {
        return new TestClassLauncher(prepareTestedClass);
    }

    public ResultsTestInfo launch(Class<?> clazz) {
        List<TestContext> testContext;
        try {
            testContext = prepareTestedClass.prepare(clazz);
        } catch (Exception e) {
            return ResultsTestInfo.buildEmpty();
        }
        List<TestDetails> details = new ArrayList<>(testContext.size());
        for (TestContext methods : testContext) {
            Optional<Throwable> init,
                    before,
                    after,
                    test;
            init = methods.init();
            before = methods.before();
            test = before.isEmpty() ? methods.test() : Optional.empty();
            after = methods.after();
            List<Throwable> listThrow = Stream.of(init, before, test, after)
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .collect(Collectors.toList());
            details.add(createDetailTest(clazz, methods, listThrow));
            System.out.println();
        }
        return ResultsTestInfo.buildFromDetails(details);
    }

    private TestDetails createDetailTest(Class<?> clazz, TestContext method, List<Throwable> listThrow) {
        TestDetails details;
        if (listThrow.isEmpty()) {
            details = TestDetails.success(clazz, method.getMethodName(), method.getClass().descriptorString());
        } else {
            Throwable throwable = getThrowable(listThrow);
            details = TestDetails.error(clazz,
                    method.getMethodName(),
                    throwable, method.getClass().descriptorString());
        }
        return details;
    }

    private Throwable getThrowable(List<Throwable> listThrow) {
        Optional<Throwable> optional = listThrow.stream().findAny();
        return optional.orElse(null);
    }
}
