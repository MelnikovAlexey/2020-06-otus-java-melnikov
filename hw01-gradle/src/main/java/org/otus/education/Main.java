package org.otus.education;

public class Main {

    public static void main(String[] args) {
        HelloOtus helloOtus = new HelloOtus();
        System.out.println(String.format("Today lucky number is %s", helloOtus.luckyNumber()));
    }
}
