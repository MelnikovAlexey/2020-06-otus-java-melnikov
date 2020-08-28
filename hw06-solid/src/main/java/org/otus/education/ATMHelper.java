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

    /**
     * @param limit устанавливаетсяя лимит купюр в касете.
     * @return
     */
    public static ATM initATM(String max) {
        int limit;
        try {
            limit =  Integer.parseInt(max);
        }catch (NumberFormatException e){
            throw new IllegalArgumentException("Invalid limit argument.");
        }

        return new ATMImpl(List.of(
                new ATMCellImpl(C100, limit, 1),
                new ATMCellImpl(C200, limit, 0),
                new ATMCellImpl(C500, limit, 19),
                new ATMCellImpl(C1000, limit, 35),
                new ATMCellImpl(C2000, limit, 20),
                new ATMCellImpl(C5000, limit, 30)
        ), new CashOutStrategyImpl(), new PutStrategyImpl());
    }



}
