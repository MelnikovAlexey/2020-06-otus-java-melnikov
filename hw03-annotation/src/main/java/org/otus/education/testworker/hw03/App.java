package org.otus.education.testworker.hw03;

import org.otus.education.testworker.PackageScannerFilterPredicate;
import org.otus.education.testworker.PackageScannerImpl;
import org.otus.education.testworker.PrepareTestedClassByAnnotation;
import org.otus.education.testworker.TestedClassLauncher;
import org.otus.education.testworker.api.PackageScanner;
import org.otus.education.testworker.inner.ResultsTestInfo;
import org.otus.education.testworker.inner.TestDetails;
import org.otus.education.testworker.inner.TotalResult;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class App {
    public static void main(String[] args) {
        PackageScanner scanner = new PackageScannerImpl("org.otus.education.testworker.hw03", new PackageScannerFilterPredicate());
        final Collection<Class<?>> scan = scanner.scan();
        TestedClassLauncher launcher = new TestedClassLauncher(new PrepareTestedClassByAnnotation());
        List<ResultsTestInfo> resultsTestInfos = new ArrayList<>();
        for (Class<?> aClass : scan) {
            ResultsTestInfo launch = launcher.launch(aClass);
            resultsTestInfos.add(launch);
        }
        System.out.println();
        TotalResult totalResult = TotalResult.buildFromResultTestByClass(resultsTestInfos);

        for (ResultsTestInfo info : resultsTestInfos) {
            if (info.isEmpty()) {
                continue;
            }
            System.out.println("Statistic by class:");
            for (TestDetails details : info.getDetailsCollection()) {
                System.out.println(details.toString());

            }
            System.out.println(info.toString());
        }
        System.out.println();
        System.out.println(totalResult.toString());
    }
}
