package org.otus.education;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;


class AppLauncherTest {

    @Test
    void serializeAndPrintStatistic() {
        assertDoesNotThrow(()->AppLauncher.serializeAndPrintStatistic(new AnyObject.Builder().build()));
    }
}