package org.otus.education.atm;

public enum CashNominal {
    C100(100),
    C200(200),
    C500(500),
    C1000(1000),
    C2000(2000),
    C5000(5000);

    private final int nominal;

    CashNominal(int nominal) {
        this.nominal = nominal;
    }

    public int getNominal() {
        return nominal;
    }
}
