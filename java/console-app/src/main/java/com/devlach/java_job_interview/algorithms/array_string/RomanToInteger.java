package com.devlach.java_job_interview.algorithms.array_string;

import java.util.Map;

public class RomanToInteger {

    public static void main(String[] args) {
        System.out.println("romanToInt(\"III\") = " + romanToInt(""));
        System.out.println("romanToInt(\"IV\") = " + romanToInt("IV"));
        System.out.println("romanToInt(\"MCMXCIV\") = " + romanToInt("MCMXCIV"));
    }

    public static int romanToInt(String s) {
        if (s == null) return 0;
        var romanValues = Map.of(
                'I', 1,
                'V', 5,
                'X', 10,
                'L', 50,
                'C', 100,
                'D', 500,
                'M', 1000
        );
        int sum = 0;
        int previousValue = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            int currentValue = romanValues.get(s.charAt(i));
            if (currentValue < previousValue) {
                sum -= currentValue;
            } else {
                sum += currentValue;
            }
            previousValue = currentValue;
        }

        return sum;
    }
}
