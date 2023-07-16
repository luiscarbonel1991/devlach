package com.devlach.patters.pipeline.fibonacci;

import java.util.List;
import java.util.stream.Stream;

public class FibonacciNumbersInputExtracStep implements Step<String, List<String>> {

    /**
     * This method is used to extract the numbers from a string
     * @param input a string
     * @return a list of strings
     */
    @Override
    public List<String> process(String input) {
        System.out.println("FibonacciNumbersInputExtracStep.process");
        System.out.printf("input: %s%n", input);
        List<String> result = Stream.of(input.split(","))
                .filter(s -> s.matches("\\d+"))
                .toList();

        System.out.printf("output: %s%n", result);
        return result;
    }
}
