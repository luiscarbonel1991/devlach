package com.devlach.patters.pipeline.fibonacci;

import java.util.List;
import java.util.stream.Stream;

public class FibonacciNumbersInputExtracStep implements Step<String, List<String>> {

    @Override
    public List<String> process(String input) {
        System.out.println("FibonacciNumbersInputExtracStep.process");
        return Stream.of(input.split(","))
                .filter( s -> s.matches("\\d+"))
                .toList();
    }
}
