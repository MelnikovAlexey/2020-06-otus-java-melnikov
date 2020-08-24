package org.otus.education;

import org.otus.education.atm.ATM;
import org.otus.education.atm.CashNominal;

import java.util.List;

import static org.otus.education.ATMHelper.*;

public class App {

    public static void main(String[] args) {
        ATM atm = initATM();
        System.out.printf("Welcome to ACME ATM! Our balance is %s.", atm.balance()).println();
        putAndPrintLog(atm, List.of(CashNominal.C1000, CashNominal.C5000));
     //   putSingletonAndPrintLog(atm, CashNominal.C200);
        putAndPrintLog(atm, List.of(CashNominal.C1000, CashNominal.C1000, CashNominal.C1000, CashNominal.C1000, CashNominal.C1000, CashNominal.C500));
        cashOutAndPrintLog(atm, 700);
        cashOutAndPrintLog(atm, -100);
        cashOutAndPrintLog(atm, 100500);
        System.out.println("Good buy! See you later.");
    }
}
