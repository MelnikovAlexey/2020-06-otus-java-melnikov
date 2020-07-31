package org.otus.education.hw03;

import org.otus.education.hw03.testworker.PackageScannerFilterPredicate;
import org.otus.education.hw03.testworker.TestRunner;

public class App {
    public static void main(String[] args) {
        TestRunner.init("org.otus.education.hw03.testedclass", new PackageScannerFilterPredicate()).run().print();
    }
}
