package org.otus.education.hw03.testworker.inner;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class ResultsTestInfo {
    private final int total;
    private final int success;
    private final int failed;
    private final Collection<TestDetails> detailsCollection;

    private ResultsTestInfo() {
        this.total = 0;
        this.success = 0;
        this.failed = 0;
        this.detailsCollection = Collections.emptyList();
    }

    private ResultsTestInfo(int total, int success, int failed, Collection<TestDetails> details) {
        this.total = total;
        this.success = success;
        this.failed = failed;
        this.detailsCollection = details;
    }

    public static ResultsTestInfo buildEmpty() {
        return new ResultsTestInfo();
    }

    public static ResultsTestInfo buildFromDetails(List<TestDetails> testDetailsList) {
        final int total = testDetailsList.size();
        final int success = (int) testDetailsList.stream().filter(TestDetails::isSuccess).count();
        final int failed = total - success;
        return new ResultsTestInfo(total, success, failed, testDetailsList);
    }

    public int getTotalTests() {
        return total;
    }

    public int getSuccessTests() {
        return success;
    }

    public int getFailedTests() {
        return failed;
    }

    public Collection<TestDetails> getDetailsCollection() {
        return detailsCollection;
    }

    public boolean isEmpty() {
        return detailsCollection.isEmpty();
    }


    @Override
    public String toString() {
        return String.format("Total Class Test running: %d%n" +
                "Success Test: %d%n" +
                "Failed Test: %d%n", total, success, failed);
    }
}
