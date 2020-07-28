package org.otus.education.testworker;

import org.otus.education.testworker.inner.ResultsTestInfo;
import org.otus.education.testworker.inner.TestDetails;
import org.otus.education.testworker.inner.TestWorkerClass;
import org.otus.education.testworker.inner.TrinityReflectMethods;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestedClassLauncher {

    private final PrepareTestedClassByAnnotation prepareTestedClass;

    public TestedClassLauncher(PrepareTestedClassByAnnotation prepareTestedClass) {
        this.prepareTestedClass = prepareTestedClass;
    }

    public ResultsTestInfo launch(Class<?> clazz) {
        TestWorkerClass testWorkerClass;
        try {
            testWorkerClass = prepareTestedClass.prepare(clazz);
        } catch (Exception e) {
            return ResultsTestInfo.buildEmpty();
        }
        List<TestDetails> details = new ArrayList<>(testWorkerClass.getReflectMethodsList().size());
        for (TrinityReflectMethods methods : testWorkerClass.getReflectMethodsList()) {
            Optional<Throwable> init, before, after, test;
            init = methods.init();
            before = methods.before();
            test = methods.test();
            after = methods.after();
            details.add(createDetailTest(clazz, methods, init, before, test, after));
            System.out.println();
        }
        return ResultsTestInfo.buildFromDetails(details);
    }

    private TestDetails createDetailTest(Class<?> clazz, TrinityReflectMethods method,
                                         Optional<Throwable> init,
                                         Optional<Throwable> before,
                                         Optional<Throwable> test,
                                         Optional<Throwable> after) {
        TestDetails details;
        List<Throwable> listThrow = Stream.of(init, before, test, after)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
        if (listThrow.isEmpty()) {
            details = TestDetails.success(clazz, method.getMethodName(), method.getClass().descriptorString());
        } else {
            details = TestDetails.error(clazz,
                    method.getMethodName(),
                    test.get(), method.getClass().descriptorString());
        }
        return details;
    }
}
