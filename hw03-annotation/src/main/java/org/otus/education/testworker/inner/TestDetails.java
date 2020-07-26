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
     * @param name имя теста
     * @return детали выполненого тестирования
     */
    public static TestDetails success(String name) {
        return new TestDetails(true, name);
    }

    /**
     * @param name      имя теста
     * @param throwable информация об ошибке тестирования
     * @return детали выполненого тестирования
     */
    public static TestDetails error(String name, Throwable throwable) {
        return new TestDetails(false, name, throwable);
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
        return String.format("Тест: %s -> %s", getName(), isSuccess() ? "Успешно" : "Провален");
    }
}
