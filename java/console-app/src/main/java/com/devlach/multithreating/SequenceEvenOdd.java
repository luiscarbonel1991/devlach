package com.devlach.multithreating;

import java.util.LinkedList;
import java.util.List;

public class SequenceEvenOdd {

    public static void main(String[] args) throws InterruptedException {
        EvenOdd evenOdd = new EvenOdd(10);
        EvenThread evenThread = new EvenThread(evenOdd);
        OddThread oddThread = new OddThread(evenOdd);
        var threads = List.of(evenThread, oddThread);
        threads.forEach(Thread::start);
        threads.forEach( thread -> {
            try {
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
        public void run(){
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
        public void run(){
            try {
                evenOdd.printOdd();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static class EvenOdd {

        private final int limit;

        private int iterator = 1;

        private final List<String> evenOdds = new LinkedList<>();

        private EvenOdd(int limit) {
            this.limit = limit;
        }

        public void printEven() throws InterruptedException {
            synchronized (this) {
                while (iterator <= limit) {
                    while (iterator % 2 != 0) {
                        wait();
                    }

                    if (iterator <= limit) {
                        System.out.println(iterator + "(even)");
                        evenOdds.add(iterator + "(even)");
                    }
                    iterator++;
                    notifyAll();
                }

            }
        }

        public  void printOdd() throws InterruptedException {
           synchronized (this) {
               while (iterator <= limit) {
                   while (iterator % 2 == 0) {
                       wait();
                   }

                   if (iterator <= limit) {
                       System.out.println(iterator + "(odd)");
                       evenOdds.add(iterator + "(odd)");
                   }
                   iterator++;
                   notifyAll();
               }
           }

        }

        public String sequence() {
            return String.join(",", evenOdds);
        }
    }
}
