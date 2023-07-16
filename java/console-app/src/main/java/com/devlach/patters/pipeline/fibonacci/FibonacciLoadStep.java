package com.devlach.patters.pipeline.fibonacci;

import java.util.List;

public class FibonacciLoadStep implements Step<List<Integer>, List<Integer>> {

    /**
     * This method is used to print the fibonacci numbers
     * @param input a list of integers
     * @return the same list of integers
     */
    @Override
    public List<Integer> process(List<Integer> input) {
        System.out.println("FibonacciLoadStep.process");
        System.out.printf("input: %s%n", input);
        System.out.printf("output: %s%n", input);
        return input;
    }
}
