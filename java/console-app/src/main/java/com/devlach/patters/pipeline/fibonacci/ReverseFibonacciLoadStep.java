package com.devlach.patters.pipeline.fibonacci;

import java.util.Comparator;
import java.util.List;

public class ReverseFibonacciLoadStep implements Step<List<Integer>, List<Integer>> {
    @Override
    public List<Integer> process(List<Integer> input) {
        System.out.println("ReverseFibonacciLoadStep.process");
        return input.stream().sorted(Comparator.reverseOrder()).toList();
    }
}
