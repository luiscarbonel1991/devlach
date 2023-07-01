package com.devlach.java_job_interview.algorithms;

import java.util.ArrayList;
import java.util.List;

/*
Given an integer n, return a string array answer (1-indexed) where:

answer[i] == "FizzBuzz" if i is divisible by 3 and 5.
answer[i] == "Fizz" if i is divisible by 3.
answer[i] == "Buzz" if i is divisible by 5.
answer[i] == i (as a string) if none of the above conditions are true.


Example 1:

Input: n = 3
Output: ["1","2","Fizz"]
Example 2:

Input: n = 5
Output: ["1","2","Fizz","4","Buzz"]
Example 3:

Input: n = 15
Output: ["1","2","Fizz","4","Buzz","Fizz","7","8","Fizz","Buzz","11","Fizz","13","14","FizzBuzz"]


Constraints:

1 <= n <= 104
 */
public class FizzBuzz {

    public static void main(String[] args) {
        System.out.println("fizzBuzz(15) = " + fizzBuzz(15));
    }

    public static List<String> fizzBuzz(int n) {
        if (n < 1 || n > 10000) {
            throw new IllegalArgumentException("n must be between 1 and 10^4");
        }

        List<String> result = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            String element = String.valueOf(i);
            if (i % 3 == 0 && i % 5 == 0) {
                element = "FizzBuzz";
            } else if (i % 3 == 0) {
                element = "Fizz";
            } else if (i % 5 == 0) {
                element = "Buzz";
            }
            result.add(element);
        }
        return result;
    }
}
