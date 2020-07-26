package org.otus.education.testworker.inner;

import java.util.List;

public class ResultsTestInfo {
    private final int total;
    private final int success;
    private final int failed;

    private ResultsTestInfo() {
        this.total = 0;
        this.success = 0;
        this.failed = 0;

    }

    private ResultsTestInfo(int total, int success, int failed) {
        this.total = total;
        this.success = success;
        this.failed = failed;
    }

    public static ResultsTestInfo initEmpty(){
        return new ResultsTestInfo();
    }

    public static ResultsTestInfo initFromDetails(List<TestDetails> testDetailsList){
        final int total = testDetailsList.size();
        final int success = (int) testDetailsList.stream().filter(TestDetails::isSuccess).count();
        final int failed = total - success;
        return new ResultsTestInfo(total,success,failed);
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
