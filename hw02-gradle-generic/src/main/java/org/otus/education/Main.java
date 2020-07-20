package org.otus.education;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        Random random = new Random(48151623);//42 не влезло последовательность из сериала LOST:)
        Integer[] randomAddArray = random
                .ints(333, 100, 500)
                .boxed().toArray(Integer[]::new);
        List<Integer> randomCopyList = random
                .ints(40, 555, 800)
                .boxed()
                .collect(Collectors.toList());
        List<Integer> dyiArrayList = new DIYarrayList<>();
        System.out.println("DIYarrayList size before Collections.addAll: " + dyiArrayList.size());
        Collections.addAll(dyiArrayList, randomAddArray);
        System.out.println("DIYarrayList size after Collections.addAll: " + dyiArrayList.size());
        System.out.println("First 40 elements DIYarrayList before Collections.copy:");
        printList(dyiArrayList);
        System.out.println();
        System.out.println("First 40 elements DIYarrayList after Collections.copy:");
        Collections.copy(dyiArrayList, randomCopyList);
        printList(dyiArrayList);
        System.out.println();
        System.out.println("First 40 elements DIYarrayList after Collections.sort:");
        Collections.sort(dyiArrayList, Comparator.naturalOrder());
        printList(dyiArrayList);
    }

    private static <E> void printList(List<E> list) {
        for (int i = 0; i < 40; i++) {
            System.out.print(list.get(i));
            if (i != 39) {
                System.out.print("->");
            }
        }
    }
}
