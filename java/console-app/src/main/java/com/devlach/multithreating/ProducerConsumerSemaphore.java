package com.devlach.multithreating;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerConsumerSemaphore {

    public static void main(String[] args) {
        Buffer buffer = new Buffer();
        Thread producer = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                try {
                    buffer.produce(i);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }
        });

        Thread consumer = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                try {
                    buffer.consume();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }
        });

        producer.start();
        consumer.start();
    }

    private static class Buffer {
        Semaphore full = new Semaphore(0);
        Semaphore empty = new Semaphore(1);

        ReentrantLock lock = new ReentrantLock();

        private Integer value = null;

        public void produce(int newValue) throws InterruptedException {
            empty.acquire();
            lock.lock();
            try {
                value = newValue;
            } finally {
                lock.unlock();
            }
            full.release();
        }

        public void consume() throws InterruptedException {
            full.acquire();
            lock.lock();
            try {
                System.out.println("value = " + value);
                value = null;
            } finally {
                lock.unlock();
            }
            empty.release();
        }
    }
}