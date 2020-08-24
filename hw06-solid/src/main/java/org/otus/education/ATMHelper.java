package org.otus.education;

import org.otus.education.atm.ATM;
import org.otus.education.atm.ATMException;
import org.otus.education.atm.CashNominal;
import org.otus.education.atm.impl.ATMCellImpl;
import org.otus.education.atm.impl.ATMImpl;
import org.otus.education.strategy.impl.CashOutStrategyImpl;
import org.otus.education.strategy.impl.PutStrategyImpl;

import java.util.List;

import static org.otus.education.atm.CashNominal.*;

public class ATMHelper {

    public static void putAndPrintLog(ATM atm, Iterable<CashNominal> collections) {
        try {
            atm.putAll(collections);
            System.out.printf("PutAll %s. Balance is %s.", collections, atm.balance()).println();
        } catch (ATMException e) {
            System.out.printf("Sorry, operation PutALL is not possible %s. Balance %s.", collections, atm.balance()).println();
        }
    }

    public static void putSingletonAndPrintLog(ATM atm, CashNominal nominal) {
        try {
            atm.put(nominal);
            System.out.printf("Put %s. Balance is %s.", nominal, atm.balance()).println();
        } catch (ATMException e) {
            System.out.printf("Sorry, operation PUT is not possible %s. Balance %s.", nominal, atm.balance()).println();
        }
    }

    public static void cashOutAndPrintLog(ATM atm, long cashOut) {
        try {
            atm.cashOut(cashOut);
            System.out.printf("CashOut %s. Balance is %s.", cashOut, atm.balance()).println();
        } catch (ATMException e) {
            System.out.printf("Sorry, operation cashOut is not possible %s. Balance %s.", cashOut, atm.balance()).println();
        }
    }

    public static ATM initATM() {
        return new ATMImpl(List.of(
                new ATMCellImpl(C100, 22, 1),
                new ATMCellImpl(C200, 30, 0),
                new ATMCellImpl(C500, 20, 19),
                new ATMCellImpl(C1000, 50, 35),
                new ATMCellImpl(C2000, 25, 20),
                new ATMCellImpl(C5000, 40, 30)
        ), new CashOutStrategyImpl(), new PutStrategyImpl());
    }

}
