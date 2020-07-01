package org.otus.education;

import com.google.common.base.Functions;
import com.google.common.base.Predicate;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.Lists;

import java.util.List;
import java.util.Random;

public class HelloOtus {
    private final  List<Integer> numbers;

    public HelloOtus() {
        System.out.println("We use google guava collection");
        this.numbers = Lists.newArrayList(1,2,3,4,5,6,7,8,9,10);
    }

    public String luckyNumber() {
        final int randomNumber = getRandomNumber();
        final List<String> list = FluentIterable.from(numbers)
                .filter( l -> l == randomNumber)
                .transform(Functions.toStringFunction())
                .toList();
        return String.join("",list);
    }

    private Integer getRandomNumber(){
        final Random random = new Random();
        final int max = 10;
        final int min = 1;
        return random.nextInt((max - min) + 1)+ min;
    }
}
