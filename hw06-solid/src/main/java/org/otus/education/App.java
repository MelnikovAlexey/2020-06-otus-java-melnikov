package org.otus.education;

import org.otus.education.atm.ATM;
import org.otus.education.atm.CashNominal;

import java.util.List;

import static org.otus.education.ATMHelper.*;

public class App {

    public static void main(String[] args) {
        if (!checkArgs(args)){
            throw new IllegalArgumentException("Limit argument not specified or more 1.");
        }
        ATM atm = initATM(args[0]);
        System.out.printf("Welcome to ACME ATM! Our balance is %s.", atm.balance()).println();
        putAndPrintLog(atm, List.of(CashNominal.C1000, CashNominal.C5000));
     //   putSingletonAndPrintLog(atm, CashNominal.C200);
        putAndPrintLog(atm, List.of(CashNominal.C1000, CashNominal.C1000, CashNominal.C1000, CashNominal.C1000, CashNominal.C1000, CashNominal.C500));
        cashOutAndPrintLog(atm, 700);
        cashOutAndPrintLog(atm, -100);
        cashOutAndPrintLog(atm, 100500);
        System.out.println("Good buy! See you later.");
    }

    private static boolean checkArgs(String[] args){
        return args.length==1;
    }
}
