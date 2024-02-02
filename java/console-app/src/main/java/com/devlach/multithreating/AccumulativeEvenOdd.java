package com.devlach.multithreating;

public class AccumulativeEvenOdd {

    public static void main(String[] args) throws InterruptedException {
        EvenOdd evenOdd = new EvenOdd();
        Thread even = new Thread(() -> {
            for (int i = 0; i <= 10; i+=2) {
                evenOdd.addEvent(i);
            }
        });

        Thread odd = new Thread(() -> {
            for (int i = 1; i < 10 ; i+=2) {
                evenOdd.addOdd(i);
            }
        });

        even.start();
        odd.start();

        even.join();
        odd.join();

        System.out.println("getSumEven = " + evenOdd.getSumEven());
        System.out.println("getSumOdd = " + evenOdd.getSumOdd());
    }

    private static class EvenOdd {
        private int sumEven;
        private int sumOdd;

        public void addEvent(int num) {
            synchronized (this) {
                this.sumEven += num;
            }
        }

        public void addOdd(int num) {
            synchronized (this) {
                this.sumOdd += num;
            }
        }

        public int getSumEven() {
            return sumEven;
        }

        public int getSumOdd() {
            return sumOdd;
        }
    }
}
