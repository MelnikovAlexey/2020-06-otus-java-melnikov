package org.otus.education.strategy.impl;

import org.otus.education.atm.ATMCell;
import org.otus.education.atm.ATMException;
import org.otus.education.atm.CashNominal;
import org.otus.education.strategy.PutStrategy;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class PutStrategyImpl implements PutStrategy {
    @Override
    public void put(Iterable<? extends ATMCell> cells, Iterable<CashNominal> nominals) {
        Map<CashNominal, Long> nominalGroupAndCount = StreamSupport.stream(nominals.spliterator(), false)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        Map<CashNominal, List<ATMCell>> cellsGroupByNominal = StreamSupport.stream(cells.spliterator(), false)
                .collect(Collectors.groupingBy(ATMCell::getCashNominal, Collectors.toList()));

        checkAvailableCellsAndFreeSpaceInCells(nominalGroupAndCount, cellsGroupByNominal);
        introduction(nominalGroupAndCount, cellsGroupByNominal);
    }

    private void checkAvailableCellsAndFreeSpaceInCells(Map<CashNominal, Long> nominalGroupAndCount
            , Map<CashNominal, List<ATMCell>> cellsGroupByNominal) {
        Set<CashNominal> nominalAvailable = cellsGroupByNominal.keySet();
        Optional<Map.Entry<CashNominal, Long>> found = Optional.empty();
        for (Map.Entry<CashNominal, Long> x : nominalGroupAndCount.entrySet()) {
            if (!nominalAvailable.contains(x.getKey()))
                throw new ATMException(String.format("Nominal '%s' not support.", x.getKey()));
            long sum = 0L;
            for (ATMCell atmCell : cellsGroupByNominal.get(x.getKey())) {
                long free = atmCell.getFree();
                sum = sum + free;
            }
            if (sum < x.getValue()) {
                found = Optional.of(x);
                break;
            }
        }
        found
                .map(Map.Entry::getKey)
                .ifPresent(nominal -> {
                            throw new ATMException(String.format("Not found free space for nominal '%s'.", nominal));
                        }
                );
    }

    private void introduction(Map<CashNominal, Long> nominalGroupAndCount, Map<CashNominal, List<ATMCell>> cellsGroupByNominal) {
        for (Map.Entry<CashNominal, Long> x : nominalGroupAndCount.entrySet()) {
            long balance = x.getValue().longValue();
            List<ATMCell> dm = cellsGroupByNominal.get(x.getKey());
            for (ATMCell ATMCell : dm) {
                int free = ATMCell.getFree();
                if (balance <= free) {
                    ATMCell.put((int) balance);
                    break;
                }
                balance -= free;
                ATMCell.put(free);
            }
        }
    }
}
