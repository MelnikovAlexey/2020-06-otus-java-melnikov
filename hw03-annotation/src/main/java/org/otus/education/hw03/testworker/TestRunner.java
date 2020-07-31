package org.otus.education.hw03.testworker;

import org.otus.education.hw03.testworker.api.PackageScanner;
import org.otus.education.hw03.testworker.inner.ResultsTestInfo;
import org.otus.education.hw03.testworker.inner.TestDetails;
import org.otus.education.hw03.testworker.inner.TotalResult;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

public class TestRunner {

    private final PackageScanner scanner;
    private List<ResultsTestInfo> resultsTestInfos;

    public TestRunner(String packageName, Predicate<Class<?>> predicate, ClassLoader loader) {
        this.scanner = PackageScannerImpl.build(packageName, predicate, loader);
    }

    private TestRunner(String packageName, Predicate<Class<?>> predicate) {
        this.scanner = PackageScannerImpl.build(packageName, predicate);
    }

    public static TestRunner init(String packageName, Predicate<Class<?>> predicate) {
        return new TestRunner(packageName, predicate);
    }

    public static TestRunner init(String packageName, Predicate<Class<?>> predicate, ClassLoader loader) {
        return new TestRunner(packageName, predicate, loader);
    }

    public TestRunner run() {
        final Collection<Class<?>> scan = scanner.scan();
        TestClassLauncher launcher = TestClassLauncher.build(new PreparerTestClassByAnnotation());
        resultsTestInfos = new ArrayList<>();
        System.out.println();
        System.out.println("Printing information about trinity: before, test, after\r\n");
        for (Class<?> aClass : scan) {
            ResultsTestInfo launch = launcher.launch(aClass);
            resultsTestInfos.add(launch);
        }
        return this;
    }

    public void print() {
        System.out.println("Now some statistics:");
        TotalResult totalResult = TotalResult.buildFromResultTestByClass(resultsTestInfos);
        for (ResultsTestInfo info : resultsTestInfos) {
            if (info.isEmpty()) {
                continue;
            }
            System.out.println("*************************\r\nStatistic by class:\r\n");
            for (TestDetails details : info.getDetailsCollection()) {
                System.out.println(details.toString());
            }
            System.out.println("\r\n" + info.toString() + "*************************");
        }
        System.out.println();
        System.out.println(totalResult.toString());
    }
}
