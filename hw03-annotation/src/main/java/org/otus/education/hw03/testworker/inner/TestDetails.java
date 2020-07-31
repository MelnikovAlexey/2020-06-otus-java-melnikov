package org.otus.education.hw03.testworker.inner;

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
     * @param name имя теста
     * @return детали выполненого тестирования
     */
    public static TestDetails success(Class<?> clazz, String name, String description) {
        return new TestDetails(clazz, true, name, description);
    }

    /**
     * @param name        имя теста
     * @param throwable   информация об ошибке тестирования
     * @param description
     * @return детали выполненого тестирования
     */
    public static TestDetails error(Class<?> clazz, String name, Throwable throwable, String description) {
        return new TestDetails(clazz, false, name, throwable, description);
    }

    public Class<?> getClazz() {
        return clazz;
    }

    /**
     * @return результат тестрирования
     */
    public boolean isSuccess() {
        return testResult;
    }

    /**
     * @return имя теста
     */
    public String getName() {
        return name;
    }

    /**
     * @return информацию об ошибке выполнения теста
     */
    public Optional<Throwable> getThrowable() {
        return Optional.ofNullable(throwable);
    }

    @Override
    public String toString() {
        return String.format("Test for %s: Method: %s -> %s", getClazz(), getName(), isSuccess() ? "Success" : "Failed because: " + getThrowable().get());
    }
}
