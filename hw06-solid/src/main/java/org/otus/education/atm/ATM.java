package org.otus.education.atm;

public interface ATM {

    void put(CashNominal nominal);

    void putAll(Iterable<CashNominal> cashs);

    void cashOut(long amount);

    long balance();
}
