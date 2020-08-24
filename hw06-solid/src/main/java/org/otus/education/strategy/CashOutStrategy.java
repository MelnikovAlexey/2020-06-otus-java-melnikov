package org.otus.education.strategy;

import org.otus.education.atm.ATMCell;

public interface CashOutStrategy {

    void cashOut(long amount, Iterable<? extends ATMCell> cells);
}
