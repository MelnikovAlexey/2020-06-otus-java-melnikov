package org.otus.education.strategy.impl;

import org.otus.education.atm.ATMCell;
import org.otus.education.atm.ATMException;
import org.otus.education.atm.CashNominal;
import org.otus.education.strategy.CashOutStrategy;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class CashOutStrategyImpl implements CashOutStrategy {

    @Override
    public void cashOut(long amount, Iterable<? extends ATMCell> cells) {
        Map<CashNominal, List<ATMCell>> cellsGroupAndSortByNominalDesc = Collections.unmodifiableSortedMap(StreamSupport.stream(cells.spliterator(), false)
                .collect(Collectors.groupingBy(ATMCell::getCashNominal, () -> new TreeMap<>(Comparator.comparingInt(CashNominal::getNominal).reversed()), Collectors.toList())));

        Collection<TaskForCashOut> tasksForCashOut = createTasksForCashOut(amount, cellsGroupAndSortByNominalDesc);
        for (TaskForCashOut taskForCashOut : tasksForCashOut) {
            taskForCashOut.execute();
        }
    }

    private Collection<TaskForCashOut> createTasksForCashOut(long amount, Map<CashNominal, List<ATMCell>> cellsGroupAndSortByNominalDesc) {
        ArrayList<TaskForCashOut> tasks = new ArrayList<>();

        long balance = amount;
        for (var nominalCells : cellsGroupAndSortByNominalDesc.entrySet()) {
            int cost = nominalCells.getKey().getNominal();
            if (cost > balance) continue;

            int cashOut = (int) (balance / cost);
            for (var workCell : nominalCells.getValue()) {
                int cashOutForCell = Math.min(workCell.getCount(), cashOut);
                if (cashOutForCell > 0) {
                    tasks.add(new TaskForCashOut(workCell, cashOutForCell));
                    balance -= cashOutForCell * cost;
                    cashOut -= cashOutForCell;
                    if (cashOut == 0) break;
                }
            }
        }
        if (balance != 0) {
            throw new ATMException(String.format("Amount '%s' unavailable.", amount));
        }
        return tasks;
    }

    private static class TaskForCashOut {

        private final ATMCell ATMCell;
        private final int cashOut;

        public TaskForCashOut(ATMCell ATMCell, int cashOut) {
            this.ATMCell = ATMCell;
            this.cashOut = cashOut;
        }

        public void execute() {
            ATMCell.cashOut(cashOut);
        }
    }
}
