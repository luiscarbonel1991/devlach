package com.devlach.patters.pipeline.fibonacci;

public class EtlPipeLine<T, R> {

    private final Step<T, R> currentStep;
    public EtlPipeLine(Step<T, R> currentStep) {
        this.currentStep = currentStep;
    }

    public <K> EtlPipeLine<T, K> addStep(Step<R, K> newStep) {
        return new EtlPipeLine<>(input -> newStep.process(currentStep.process(input)));
    }

    public R execute(T input) {
        return currentStep.process(input);
    }

}
