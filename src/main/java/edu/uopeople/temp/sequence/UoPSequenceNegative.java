package edu.uopeople.temp.sequence;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class UoPSequenceNegative {

    public static void main(String[] args) {
        List<List<Integer>> sequences = new ArrayList<>();

        createNumbers()
                .forEach(number -> sequences.add(createSequence(number)));
        showSequences(sequences);
    }

    private static List<Integer> createSequence(int number) {
        List<Integer> sequence = new ArrayList<>();
        sequence.add(number);

        while (number < -1) {
            number = updateNumber(number);
            sequence.add(number);
        }
        return sequence;
    }

    private static void showSequences(List<List<Integer>> sequences) {
        sequences.forEach(System.out::println);
    }

    private static int updateNumber(int number) {
        if (isEven(number)) {
            return number / 2;
        } else {
            return number * 3 - 1;
        }
    }

    private static List<Integer> createNumbers() {
        return new Random().ints(10, -100, -2)
                .boxed()
                .collect(Collectors.toList());
    }

    private static boolean isEven(int number) {
        return (number ^ 1) > number;
    }
}
