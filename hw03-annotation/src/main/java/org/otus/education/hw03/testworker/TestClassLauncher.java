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

    private final PreparerTestClassByAnnotation preparerClass;

    private TestClassLauncher(PreparerTestClassByAnnotation preparerClass) {
        this.preparerClass = preparerClass;
    }

    public static TestClassLauncher build(PreparerTestClassByAnnotation prepareTestedClass) {
        return new TestClassLauncher(prepareTestedClass);
    }

    public ResultsTestInfo launch(Class<?> clazz) {

        List<TestContext> ctxList;
        try {
            ctxList = preparerClass.prepare(clazz);
        } catch (Exception e) {
            return ResultsTestInfo.buildEmpty();
        }

        List<TestDetails> details = new ArrayList<>(ctxList.size());
        for (TestContext ctx : ctxList) {

            Optional<Throwable> creationError = ctx.createTestInstance();
            Optional<Throwable> beforeMethodExecutionError = ctx.executeBeforeMethod();
            Optional<Throwable> testMethodExecutionError = beforeMethodExecutionError.isEmpty() ?
                    ctx.executeTestMethod() : Optional.empty();
            Optional<Throwable> afterMethodExecutionError = ctx.executeAfterMethod();

            List<Throwable> listThrow = Stream.of(creationError,
                    beforeMethodExecutionError,
                    testMethodExecutionError,
                    afterMethodExecutionError)
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .collect(Collectors.toList());

            details.add(createDetailTest(clazz, ctx, listThrow));
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
