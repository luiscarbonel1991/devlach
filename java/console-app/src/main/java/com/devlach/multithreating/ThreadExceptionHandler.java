package com.devlach.multithreating;

public class ThreadExceptionHandler {

    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
           throw new RuntimeException("Intentional Exception");
        });

        thread.setName("Uncaught Exception Thread");
        thread.setUncaughtExceptionHandler((t, e) -> System.out.printf("A critical error happened in thread [%s] with the following message: [%s]", t.getName(), e.getMessage()));

        thread.start();
    }
}
