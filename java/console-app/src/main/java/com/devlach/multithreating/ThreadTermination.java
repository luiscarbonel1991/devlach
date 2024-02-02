package com.devlach.multithreating;

import java.math.BigInteger;

public class ThreadTermination {

    public static void main(String[] args) {
        /*Thread thread = new Thread(new BlockingTask());
        thread.start();
        thread.interrupt();*/

        Thread longComputation = new Thread(new LongComputation(BigInteger.valueOf(20000L), BigInteger.valueOf(500000000)));

        longComputation.start();
        longComputation.interrupt(); // If we call to interrupt, nothing happens. We have to handle the stop of calculation.
    }

    private static class BlockingTask implements Runnable {

        @Override
        public void run() {
            try {
                Thread.sleep(100_000);
            } catch (InterruptedException e) {
                System.out.println("BlockingTask.run. Executing blocking task");
            }
        }
    }

    private static class LongComputation implements Runnable {

        private BigInteger base;
        private BigInteger power;

        public LongComputation(BigInteger base, BigInteger power) {
            this.base = base;
            this.power = power;
        }

        @Override
        public void run() {
            System.out.printf("%d ^ %d = %d%n", base, power, pow(base, power));
        }

        /*private BigInteger pow(BigInteger base, BigInteger power) {
            BigInteger result = BigInteger.ONE;
            for (BigInteger i = BigInteger.ZERO; i.compareTo(power) !=0 ; i = i.add(BigInteger.ONE)) {
                result = result.multiply(base);
            }

            return result;
        }*/

        private BigInteger pow(BigInteger base, BigInteger power) {
            BigInteger result = BigInteger.ONE;
            for (BigInteger i = BigInteger.ZERO; i.compareTo(power) !=0 ; i = i.add(BigInteger.ONE)) {
                if(Thread.currentThread().isInterrupted()) {
                    System.out.println("Prematurely interrupt computation");
                    return BigInteger.ZERO;
                }
                result = result.multiply(base);
            }

            return result;
        }
    }
}
