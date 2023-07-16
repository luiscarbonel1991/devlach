package com.devlach.patters.pipeline.fibonacci;

import java.util.Comparator;
import java.util.List;

public class FibonacciTransformStep implements Step<List<String>, List<Integer>> {

    /**
     * This method is used to calculate the fibonacci numbers from a list of strings
     * and sort them in descending order
     * @param input a list of strings
     * @return a list of integers sorted in descending order
     */
    @Override
    public List<Integer> process(List<String> input) {
        System.out.println("FibonacciTransformStep.process");
        System.out.printf("input: %s%n", input);
        List<Integer> result = input.stream()
                .map(Integer::parseInt)
                .map(FibonacciTransformStep::fibonacci)
                .sorted(Comparator.reverseOrder())
                .toList();

        System.out.printf("output: %s%n", result);
        return result;
    }

    private static Integer fibonacci(Integer n) {
        if (n <= 1) return n;
        return fibonacci(n-1) + fibonacci(n-2);
    }
}
