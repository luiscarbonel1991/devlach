package com.devlach.multithreating;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ProducerConsumerEvenOdd {

    public static void main(String[] args) {
        BlockingQueue<Integer> queue = new LinkedBlockingQueue<>();

        Thread evenProducer = new Thread(new NumberProducer(queue, 0, 100, 2));
        Thread oddProducer = new Thread(new NumberProducer(queue, 1, 100, 2));
        Thread consumer = new Thread( new NumberConsumer(queue));

        evenProducer.start();
        oddProducer.start();
        consumer.start();
    }

    private static class NumberProducer implements Runnable {

        private final BlockingQueue<Integer> blockingQueue;
        private final int start;
        private final int end;

        private final int step;

        public NumberProducer(BlockingQueue<Integer> blockingQueue, int start, int end, int step) {
            this.blockingQueue = blockingQueue;
            this.start = start;
            this.end = end;
            this.step = step;
        }

        @Override
        public void run() {
            for (int i = start; i <= end; i += step) {
                try {
                    blockingQueue.put(i);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.out.println("Producer was interrupted");
                }
            }
        }
    }

    private static class NumberConsumer implements Runnable {

        private final BlockingQueue<Integer> blockingQueue;

        public NumberConsumer(BlockingQueue<Integer> blockingQueue) {
            this.blockingQueue = blockingQueue;
        }


        @Override
        public void run() {
            while (!blockingQueue.isEmpty()) {
                try {
                    Integer number = blockingQueue.take();
                    System.out.println("Consume number = " + number);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.out.println("Consumer was interrupted");
                    return;
                }
            }
        }
    }
}
