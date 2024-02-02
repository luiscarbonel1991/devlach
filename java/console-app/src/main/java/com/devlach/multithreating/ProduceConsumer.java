package com.devlach.multithreating;

public class ProduceConsumer {


    public static void main(String[] args) {
        Buffer buffer = new Buffer();
        Thread producer = new Thread( new Producer(buffer));
        Thread consumer = new Thread( new Consumer(buffer));

        producer.start();
        consumer.start();
    }

    private record Producer(Buffer buffer) implements Runnable {

        @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    try {
                        this.buffer.produce(i);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }

    private record Consumer(Buffer buffer) implements Runnable {

        @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    try {
                        buffer.consumer();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }

    private static class Buffer {
        private Integer value = null;

        public synchronized void produce(int newValue) throws InterruptedException {
            while (this.value != null) {
                wait();
            }
            this.value = newValue;
            System.out.println("Produce newValue = " + newValue);
            notifyAll();
        }

        public synchronized void consumer() throws InterruptedException {
            while (this.value == null) {
                wait();
            }
            System.out.println("Consume newValue = " + value);
            this.value = null;
            notifyAll();
        }
    }
}
