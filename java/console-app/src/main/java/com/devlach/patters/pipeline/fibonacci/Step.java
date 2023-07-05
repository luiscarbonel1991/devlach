package com.devlach.patters.pipeline.fibonacci;

public interface Step<T, R> {
    R process(T input);
}
