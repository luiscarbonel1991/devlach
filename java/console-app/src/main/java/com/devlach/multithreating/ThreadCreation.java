package com.devlach.multithreating;

public class ThreadCreation {

    public static void main(String[] args) throws InterruptedException {
        Thread thead = new Thread(new Runnable() { // this is the thread that will be created
            @Override
            public void run() {
                System.out.println("We are in thread: " + Thread.currentThread().getName());
                System.out.println("Current thread priority is: " + Thread.currentThread().getPriority());
            }
        });

        thead.setName("New Worker Thread"); // this is the name of the thread
        thead.setPriority(Thread.MAX_PRIORITY); // This is the priority of the thread. Set to 10
        System.out.println("We are in thread: " + Thread.currentThread().getName() + " before starting a new thread");
        thead.start(); // this is the method that starts the thread
        System.out.println("We are in thread: " + Thread.currentThread().getName() + " after starting a new thread");


        Thread newThread = new NewThread("New Thread");
        newThread.start();
    }

    private static class NewThread extends Thread {

        public NewThread(String name) {
            super(name); // this is the name of the thread
        }

        @Override
        public void run() { // this is the method that will be executed when the thread is started
            System.out.println("Hello from " + this.getName());
        }
    }
}
