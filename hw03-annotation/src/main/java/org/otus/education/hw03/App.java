package org.otus.education.hw03;

import org.otus.education.testworker.PackageScannerFilterPredicate;
import org.otus.education.testworker.PackageScannerImpl;
import org.otus.education.testworker.PreparerTestClassByAnnotation;
import org.otus.education.testworker.TestClassLauncher;
import org.otus.education.testworker.api.PackageScanner;
import org.otus.education.testworker.inner.ResultsTestInfo;
import org.otus.education.testworker.inner.TestDetails;
import org.otus.education.testworker.inner.TotalResult;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class App {
    public static void main(String[] args) {
        PackageScanner scanner = PackageScannerImpl.build("org.otus.education.hw03", new PackageScannerFilterPredicate());
        final Collection<Class<?>> scan = scanner.scan();
        TestClassLauncher launcher = TestClassLauncher.build(new PreparerTestClassByAnnotation());
        List<ResultsTestInfo> resultsTestInfos = new ArrayList<>();
        System.out.println();
        System.out.println("Printing information about trinity: before, test, after\r\n");
        for (Class<?> aClass : scan) {
            ResultsTestInfo launch = launcher.launch(aClass);
            resultsTestInfos.add(launch);
        }

        printStatistic(resultsTestInfos);
    }

    private static void printStatistic(List<ResultsTestInfo> resultsTestInfos) {
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
            System.out.println("\r\n"+ info.toString()+"*************************");
        }
        System.out.println();
        System.out.println(totalResult.toString());
    }
}
