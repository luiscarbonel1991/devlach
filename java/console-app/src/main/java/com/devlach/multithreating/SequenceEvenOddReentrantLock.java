package com.devlach.multithreating;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SequenceEvenOddReentrantLock {

    public static void main(String[] args) throws InterruptedException {
        EvenOdd evenOdd = new EvenOdd(0, 10);
        EvenThread evenThread = new EvenThread(evenOdd);
        OddThread oddThread = new OddThread(evenOdd);
        var threads = List.of(evenThread, oddThread);
        threads.forEach(Thread::start);
        System.out.println("sequence = " + evenOdd.sequence());
        threads.forEach(thread -> {
            try {
                System.out.println("sequence = " + evenOdd.sequence());
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        System.out.println("sequence = " + evenOdd.sequence());
    }

    private static class EvenThread extends Thread {

        private final EvenOdd evenOdd;

        public EvenThread(EvenOdd evenOdd) {
            this.evenOdd = evenOdd;
        }

        @Override
        public void run() {
            try {
                evenOdd.printEven();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static class OddThread extends Thread {

        private final EvenOdd evenOdd;

        public OddThread(EvenOdd evenOdd) {
            this.evenOdd = evenOdd;
        }

        @Override
        public void run() {
            try {
                evenOdd.printOdd();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static class EvenOdd {


        private int number;
        private final int maxNumber;

        private final List<String> sequence = new LinkedList<>();

        public EvenOdd(int number, int maxNumber) {
            this.number = number;
            this.maxNumber = maxNumber;
        }

        public void printEven() throws InterruptedException {

            synchronized (this) {
                while (number <= maxNumber) {
                    while (number % 2 != 0) {
                        wait();
                    }
                    if(number <= maxNumber) {
                        System.out.println(number + "(even)");
                        sequence.add(number + "(even)");
                    }
                    number++;
                    notifyAll();
                }
            }


        }

        public void printOdd() throws InterruptedException {
            synchronized (this) {
                while (number <= maxNumber) {
                    while (number % 2 == 0 ) {
                        wait();
                    }

                    if(number <= maxNumber) {
                        System.out.println(number + "(odd)");
                        sequence.add(number + "(odd)");
                    }
                    number++;
                    notifyAll();
                }
            }
        }

        public String sequence() {
            return String.join(",", sequence);
        }
    }
}
