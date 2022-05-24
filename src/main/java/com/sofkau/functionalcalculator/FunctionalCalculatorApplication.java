package com.sofkau.functionalcalculator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.stream.IntStream;
import java.util.stream.Stream;

@SpringBootApplication
public class FunctionalCalculatorApplication {

    public static void main(String[] args) {
        SpringApplication.run(FunctionalCalculatorApplication.class, args);
        System.out.println("\n" +
                "\n*** Simple Functional Programming ***");
        IOperate addition = (x, y) -> x + y;
        IOperate subtraction = (x, y) -> x - y;
        IOperate multiplication = (x, y) -> IntStream
                .iterate(x, n -> addition.operate(0, x))
                .limit(y).reduce(0, (n, m) -> n + m);
        IOperate division = (x, y) -> {
            if (y == 0) throw new IllegalArgumentException("Can't divide by cero.");
            return IntStream.iterate(x, n -> n >= 0, n -> subtraction.operate(n, y)).toArray().length - 1;
        };

        System.out.println("Addition examples:" +
                "\n 5 + 10 = " + addition.operate(5, 10) +
                "\n 3 + -2 = " + addition.operate(3, -2));
        System.out.println("\nMultiplication examples:" +
                "\n 2 x 2 = " + multiplication.operate(2, 2) +
                "\n 10 x 0 = " + multiplication.operate(10, 0));
        System.out.println("\nSubtraction examples:" +
                "\n 20 - 10 = " + subtraction.operate(20, 10) +
                "\n 12 - 15 = " + subtraction.operate(12, 15));
        System.out.println("\nDivision examples:" +
                "\n 10 / 2 = " + division.operate(10, 2) +
                "\n 10 / 0 = ");
        System.out.println(division.operate(10, 0));
    }

}
