package org.otus.education.atm.impl;

import org.otus.education.atm.ATMCell;
import org.otus.education.atm.ATMException;
import org.otus.education.atm.CashNominal;

public class ATMCellImpl implements ATMCell {
    private final CashNominal nominal;
    private final int limit;
    private int count;

    public ATMCellImpl(CashNominal nominal, int limit, int count) {
        if (nominal == null)
            throw new IllegalArgumentException("CashNominal cannot be null.");
        if (count < 0)
            throw new IllegalArgumentException("Count mustn't be less 0.");
        if (count > limit)
            throw new IllegalArgumentException("Count mustn't be less or equals limit.");
        if (limit < 1)
            throw new IllegalArgumentException("Limit mustn't be less 1.");

        this.nominal = nominal;
        this.limit = limit;
        this.count = count;
    }

    @Override
    public CashNominal getCashNominal() {
        return nominal;
    }

    @Override
    public void put(int count) {
        if (count <= 0)
            throw new ATMException(new IllegalArgumentException("Count cannot be less 1."));
        if (!check(count))
            throw new ATMException("Cell full.");
        this.count+=count;
    }

    @Override
    public void cashOut(int count) {
        if (count < 0 || this.count < count)
            throw new ATMException(new IllegalArgumentException("Count mustn't be less 0 and more cell.count"));
        this.count -=count;
    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public long getSum() {
        return getCashNominal().getNominal() * getCount();
    }

    @Override
    public int getLimit() {
        return limit;
    }

    @Override
    public int getFree() {
        return limit - count;
    }

    @Override
    public boolean isEmpty() {
        return getCount()==0;
    }

    private boolean check(int count) {
        int sum = this.count + count;
        return sum >= 0 && sum <= limit;
    }
}
