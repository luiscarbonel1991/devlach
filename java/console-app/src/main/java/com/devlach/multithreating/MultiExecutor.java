package com.devlach.multithreating;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class MultiExecutor {

    public static void main(String[] args) {
        Runnable task1 = () -> {
          System.out.println("I'm Thread: " + Thread.currentThread().getName());
        };

        Runnable task2 = () -> {
            System.out.println("I'm Thread: " + Thread.currentThread().getName());
        };
        Runnable task3 = () -> {
            System.out.println("I'm Thread: " + Thread.currentThread().getName());
        };
        MultiExecutor executor = new MultiExecutor(
               List.of(task1, task2, task3)
        );

        executor.executeAll();
    }

    // Add any necessary member variables here
    private final List<Thread> tasks;

    /*
     * @param tasks to executed concurrently
     */
    public MultiExecutor(List<Runnable> tasks) {
        // Complete your code here
        if(Objects.isNull(tasks)) throw new IllegalArgumentException("Tasks can not be empty null");
        this.tasks = tasks.stream().map(Thread::new).collect(Collectors.toList());
    }

    /**
     * Starts and executes all the tasks concurrently
     */
    public void executeAll() {
        // complete your code here
        tasks.forEach(Thread::start);
    }
}
