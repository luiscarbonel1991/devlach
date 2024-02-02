package com.devlach.multithreating;

import java.util.concurrent.atomic.AtomicInteger;

public class ThreadSharedInventoryCounter {

    public static void main(String[] args) throws InterruptedException {
        InventoryCounterSync  counter = new InventoryCounterSync();
        IncrementingThread incrementingThread = new IncrementingThread(counter);
        DecrementingThread decrementingThread = new DecrementingThread(counter);

        incrementingThread.start();
        decrementingThread.start();


        incrementingThread.join();
        decrementingThread.join();

        System.out.println("counter = " + counter.getItems());
    }

    public static class IncrementingThread extends Thread {

        private InventoryCounterSync inventoryCounter;

        public IncrementingThread(InventoryCounterSync counter) {
            this.inventoryCounter = counter;
        }

        @Override
        public void run() {
            for (int i = 0; i < 1000; i++) {
                inventoryCounter.increment();
            }
        }
    }

    public static class DecrementingThread extends Thread {

        private final InventoryCounterSync inventoryCounter;

        public DecrementingThread(InventoryCounterSync counter) {
            this.inventoryCounter = counter;
        }

        @Override
        public void run() {
            for (int i = 0; i < 1000; i++) {
                inventoryCounter.decrement();
            }
        }
    }

    private static class InventoryCounter {

        private final AtomicInteger items = new AtomicInteger(); // if this variable it is not atomic, then the items variable will be overridden for each Thread

        public void increment() {
            items.incrementAndGet();
        }

        public void decrement() {
            items.decrementAndGet();
        }

        public int getItems() {
            return items.get();
        }
    }

    private static class InventoryCounterSync {

        final Object lock1 = new Object();
        private int items = 0;

        public void increment() {
           synchronized (this.lock1) {
               items++;
           }
        }

        public void decrement() {
           synchronized (lock1) {
               items--;
           }
        }

        public int getItems() {
            return items;
        }
    }
}
