package org.otus.education.hw03.testworker.inner;

import java.util.Collection;

public class TotalResult {
    private final int total;
    private final int success;
    private final int failed;


    private TotalResult(int total, int success, int failed) {
        this.total = total;
        this.success = success;
        this.failed = failed;
    }

    public static TotalResult buildFromResultTestByClass(Collection<ResultsTestInfo> collection) {
        int total = 0, success = 0, failed = 0;
        if (!collection.isEmpty()) {
            for (ResultsTestInfo resultsTestInfo : collection) {
                total = total + resultsTestInfo.getTotalTests();
                success = success + resultsTestInfo.getSuccessTests();
                failed = failed + resultsTestInfo.getFailedTests();
            }
        }
        return new TotalResult(total, success, failed);
    }

    @Override
    public String toString() {
        return "Total Statistic:\r\n" +
                "Total tests: " + total +
                "\r\nSuccess tests: " + success +
                "\r\nFailed tests: " + failed;
    }
}
