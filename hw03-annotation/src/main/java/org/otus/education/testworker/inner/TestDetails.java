package org.otus.education.testworker.inner;

import java.util.Optional;

public class TestDetails {
    private final boolean testResult;
    private final String name;
    private final Throwable throwable;

    private TestDetails(boolean testResult, String name, Throwable throwable) {
        this.testResult = testResult;
        this.name = name;
        this.throwable = throwable;
    }

    private TestDetails(boolean testResult, String name) {
        this(testResult, name, null);
    }

    /**
     * @param name ��� �����
     * @return ������ ����������� ������������
     */
    public static TestDetails success(String name) {
        return new TestDetails(true, name);
    }

    /**
     * @param name      ��� �����
     * @param throwable ���������� �� ������ ������������
     * @return ������ ����������� ������������
     */
    public static TestDetails error(String name, Throwable throwable) {
        return new TestDetails(false, name, throwable);
    }

    /**
     * @return ��������� �������������
     */
    public boolean isSuccess() {
        return testResult;
    }

    /**
     * @return ��� �����
     */
    public String getName() {
        return name;
    }

    /**
     * @return ���������� �� ������ ���������� �����
     */
    public Optional<Throwable> getThrowable() {
        return Optional.ofNullable(throwable);
    }

    @Override
    public String toString() {
        return String.format("����: %s -> %s", getName(), isSuccess() ? "�������" : "��������");
    }
}
