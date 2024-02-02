package com.devlach.multithreating;

import java.math.BigInteger;
import java.util.List;


public class ThreadCoordination {


    public static void main(String[] args) throws  InterruptedException {
     /*   List<Long> inputNumbers = List.of(200000L, 3435L, 35435L, 4656L, 20L, 5556L);

        List<FactorialThread> threads = inputNumbers.stream().map(FactorialThread::new).toList();
        threads.forEach( t -> {
            // t.setDaemon(true);  If it is false, the application will be waiting for factorial of 200000L
            t.start();
        });
        for (Thread thread: threads) {
            thread.join(2000);
        }

       for (int i = 0; i < inputNumbers.size(); i++) {
           FactorialThread thread = threads.get(i);
           if(thread.isFinished()) {
               System.out.println("Factorial of " + inputNumbers.get(i) + " is " + thread.getResult());
           } else {
               System.out.println("The calculation for " + inputNumbers.get(i) + " is still in progress");
           }
       }

       FactorialThread firstThread = threads.get(0);
       while (!firstThread.isFinished()) {
           System.out.printf("Waiting for the computation of %d", firstThread.inputNumber );
           Thread.sleep(2000);
       }

        System.out.printf("The  computation of %d finally is: %s", firstThread.inputNumber, firstThread.getResult() );
*/
        var result = calculateResult(BigInteger.valueOf(20L), BigInteger.valueOf(2L), BigInteger.valueOf(20L), BigInteger.valueOf(2L));
        System.out.println("Result of calculateResult is: " + result);
    }

    private static class FactorialThread extends Thread {

        private final long inputNumber;
        private BigInteger result = BigInteger.ZERO;
        private boolean isFinished = false;


        public FactorialThread(long inputNumber) {
            this.inputNumber = inputNumber;
        }

        @Override
        public void run() {
            this.result = factorial(inputNumber);
            this.isFinished = true;
        }

        public BigInteger getResult() {
            return result;
        }

        public boolean isFinished() {
            return isFinished;
        }

        private BigInteger factorial(long inputNumber) {
            BigInteger tmpResult = BigInteger.ONE;

            for (long i = inputNumber; i > 0; i --) {
                tmpResult = tmpResult.multiply(BigInteger.valueOf(i));
            }
            return tmpResult;
        }


    }

    public static BigInteger calculateResult(BigInteger base1, BigInteger power1, BigInteger base2, BigInteger power2) {
        BigInteger result;
        /*
            Calculate result = ( base1 ^ power1 ) + (base2 ^ power2).
            Where each calculation in (..) is calculated on a different thread
        */
        List<PowerCalculatingThread> threads = List.of(
                new PowerCalculatingThread(base1, power1),
                new PowerCalculatingThread(base2, power2)
        );
        threads.forEach(PowerCalculatingThread::start);
        for (PowerCalculatingThread thread: threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        return threads.stream().map(PowerCalculatingThread::getResult).reduce(BigInteger::add).orElse(BigInteger.ZERO);
    }

    private static class PowerCalculatingThread extends Thread {
        private BigInteger result = BigInteger.ONE;
        private BigInteger base;
        private BigInteger power;

        public PowerCalculatingThread(BigInteger base, BigInteger power) {
            this.base = base;
            this.power = power;
        }

        @Override
        public void run() {
           /*
           Implement the calculation of result = base ^ power
           */

            for (BigInteger i = BigInteger.ZERO; i.compareTo(power) !=0 ; i = i.add(BigInteger.ONE)) {
                result = result.multiply(base);
            }
        }

        public BigInteger getResult() { return result; }
    }
}
