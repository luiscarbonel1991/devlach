package com.devlach.java_job_interview.algorithms;

public class Factorial {

    public static void main(String[] args) {
        // factorial(0) = 1
        // factorial(1) = 1
        // factorial(2) = 2 * 1 = 2
        // factorial(20) = 20 * 19 * 18 * ... * 1
        System.out.println("factorial(4) = " + factorial(10));
        System.out.println("factorialWithOutRecursion(4) = " + factorialWithOutRecursion(10));
    }

    public static long factorial(long n) {
        /*
        The factorial of any number is that number by the factorial of the previous number.
        This can be expressed in a formula like. = n (n - 1)! .
         */
        // Rules:
        // 0! = 1
        // 1! = 1
        //n! = n * (n - 1)!
        if (n <= 1) {
            return 1;
        }
        return n * factorial(n - 1);
    }

    public static long factorialWithOutRecursion(long n) {
        long factorial = 1;
        for(int i = 1; i <= n; i ++) {
            factorial = factorial * i;
        }
        return factorial;
    }
}
