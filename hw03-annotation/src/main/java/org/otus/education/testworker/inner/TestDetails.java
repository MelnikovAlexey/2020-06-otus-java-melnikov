package org.otus.education.testworker.inner;

import java.util.Optional;

public class TestDetails {
    private final Class<?> clazz;
    private final boolean testResult;
    private final String name;
    private final Throwable throwable;
    private final String description;

    private TestDetails(Class<?> clazz, boolean testResult, String name, Throwable throwable, String description) {
        this.clazz = clazz;
        this.testResult = testResult;
        this.name = name;
        this.throwable = throwable;
        this.description = description;
    }

    private TestDetails(Class<?> clazz, boolean testResult, String name, String description) {
        this(clazz, testResult, name, null, description);
    }

    /**
     * @param name ��� �����
     * @return ������ ����������� ������������
     */
    public static TestDetails success(Class<?> clazz, String name, String description) {
        return new TestDetails(clazz, true, name, description);
    }

    /**
     * @param name        ��� �����
     * @param throwable   ���������� �� ������ ������������
     * @param description
     * @return ������ ����������� ������������
     */
    public static TestDetails error(Class<?> clazz, String name, Throwable throwable, String description) {
        return new TestDetails(clazz, false, name, throwable, description);
    }

    public Class<?> getClazz() {
        return clazz;
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
        return String.format("Test for %s: Method: %s -> %s", getClazz(), getName(), isSuccess() ? "Success" : "Failed");
    }
}
