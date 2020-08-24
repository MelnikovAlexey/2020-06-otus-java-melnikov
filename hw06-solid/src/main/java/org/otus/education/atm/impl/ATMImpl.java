package org.otus.education.atm.impl;

import org.otus.education.atm.ATM;
import org.otus.education.atm.ATMCell;
import org.otus.education.atm.CashNominal;
import org.otus.education.strategy.CashOutStrategy;
import org.otus.education.strategy.PutStrategy;

import java.util.Collections;
import java.util.List;

public class ATMImpl implements ATM {

    private final List<? extends ATMCell> cells;
    private final CashOutStrategy cashOutStrategy;
    private final PutStrategy putStrategy;


    public ATMImpl(List<? extends ATMCell> cells, CashOutStrategy cashOutStrategy, PutStrategy putStrategy) {
        this.cells = cells;
        this.cashOutStrategy = cashOutStrategy;
        this.putStrategy = putStrategy;
    }

    @Override
    public void put(CashNominal nominal) {
        this.putAll(Collections.singleton(nominal));
    }

    @Override
    public void putAll(Iterable<CashNominal> nominals) {
        putStrategy.put(cells,nominals);
    }

    @Override
    public void cashOut(long amount) {
        cashOutStrategy.cashOut(amount,cells);
    }

    @Override
    public long balance() {
        return cells.stream().mapToLong(ATMCell::getSum).sum();
    }
}
