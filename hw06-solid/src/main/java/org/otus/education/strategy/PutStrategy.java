package org.otus.education.strategy;

import org.otus.education.atm.ATMCell;
import org.otus.education.atm.CashNominal;

public interface PutStrategy {

    void put(Iterable<? extends ATMCell> cells, Iterable<CashNominal> nominals);
}
