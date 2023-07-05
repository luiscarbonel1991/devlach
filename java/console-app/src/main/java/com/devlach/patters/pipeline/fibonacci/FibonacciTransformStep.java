package com.devlach.patters.pipeline.fibonacci;

import java.util.List;

public class FibonacciTransformStep implements Step<List<String>, List<Integer>> {
    @Override
    public List<Integer> process(List<String> input) {
        System.out.println("FibonacciTransformStep.process");
        return input.stream()
                .map(Integer::parseInt)
                .map(FibonacciTransformStep::fibonacci)
                .toList();
    }

    private static Integer fibonacci(Integer n) {
        if (n <= 1) return n;
        return fibonacci(n-1) + fibonacci(n-2);
    }
}
