package edu.uopeople.temp.simple.fibonacci;

import org.openjdk.jmh.annotations.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Fibonacci numbers calculation with benchmarking.
 */
@State(Scope.Benchmark)
public class Fibonacci {

    @Param({"10", "20"})
    public int maxInput;

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    @Fork(value = 1)
    @Warmup(iterations = 1)
    @Measurement(iterations = 1)
    public void init1() {
        fib1(maxInput);
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    @Fork(value = 1)
    @Warmup(iterations = 1)
    @Measurement(iterations = 1)
    public void init2() {
        fib2(maxInput);
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    @Fork(value = 1)
    @Warmup(iterations = 1)
    @Measurement(iterations = 1)
    public void init3() {
        fib3(maxInput);
    }

    static class BenchmarkRunner {
        public static void main(String[] args) throws Exception {
            org.openjdk.jmh.Main.main(args);
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {

        Fibonacci fibonacci = new Fibonacci();
        System.out.println(fibonacci.fib1(25));
        Thread.sleep(1000L);
        System.out.println(fibonacci.fib2(25));
        Thread.sleep(1000L);
        System.out.println(fibonacci.fib3(25));


    }

    /**
     * Naive recursive approach. Slow and limited.
     */
    public int fib1(int input) {
        if (input < 2) {
            return input;
        }
        return fib1(input - 1) + fib1(input - 2);
    }

    /**
     * Improved by memoization recursive approach.
     */
    Map<Integer, Integer> memoryMap = new HashMap<>(Map.of(0, 0, 1, 1));

    public int fib2(int input) {
        if (!memoryMap.containsKey(input)) {
            memoryMap.put(input, fib2(input - 1) + fib2(input - 2));
        }
        return memoryMap.get(input);
    }

    /**
     * Iterative approach. Fastest.
     */
    public int fib3(int input) {
        int last = 0, next = 1;
        for (int i = 0; i < input; i++) {
            int oldLast = last;
            last = next;
            next = oldLast + next;
        }
        return last;
    }
}
