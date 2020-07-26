package org.otus.education.testworker.inner;

public class ResultsTestInfo {
    private final int total;
    private final int success;
    private final int failed;

    private ResultsTestInfo() {
        this.total = 0;
        this.success = 0;
        this.failed = 0;
    }

    public static ResultsTestInfo initEmpty(){
        return new ResultsTestInfo();
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
}
