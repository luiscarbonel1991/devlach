package com.devlach.java_job_interview.algorithms;

public class Prime {

    public static void main(String[] args) {
        System.out.println("isPrime = " + isPrime(1));
        System.out.println("isPrime = " + isPrime(3));
    }

    private static boolean isPrime(int n) {
        boolean isPrime = n > 1;
        for(int i = 2; i < n; i ++) {
            if(n % i == 0) {
                isPrime = false;
                break;
            }
        }
        return isPrime;
    }
}
