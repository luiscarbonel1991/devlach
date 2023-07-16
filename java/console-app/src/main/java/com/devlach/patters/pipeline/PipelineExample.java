package com.devlach.patters.pipeline;

import com.devlach.patters.pipeline.fibonacci.EtlPipeLine;
import com.devlach.patters.pipeline.fibonacci.FibonacciNumbersInputExtracStep;
import com.devlach.patters.pipeline.fibonacci.FibonacciTransformStep;
import com.devlach.patters.pipeline.fibonacci.FibonacciLoadStep;

public class PipelineExample {
    public static void main(String[] args) {
        var pipeline = new EtlPipeLine<>(new FibonacciNumbersInputExtracStep())
                .addStep(new FibonacciTransformStep())
                .addStep(new FibonacciLoadStep());

        pipeline.execute("hsdhs,sds,1,2,a,3,4,5,6,7,8,9,10");
    }
}
