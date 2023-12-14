package com.devlach.java_job_interview.algorithms.array_string;

public class IntegerToRoman {

    public static void main(String[] args) {
        System.out.println("intToRoman(3) = " + intToRoman(3));
        System.out.println("intToRoman(4) = " + intToRoman(4));
        System.out.println("intToRoman(9) = " + intToRoman(9));
        System.out.println("intToRoman(58) = " + intToRoman(58));
        System.out.println("intToRoman(1994) = " + intToRoman(1994));
    }

    /**
     * Given an integer, convert it to a roman numeral.
     *
     * @param num 1 <= num <= 3999
     * @return Roman numeral
     */
    public static String intToRoman(int num) {
        if (num < 1 || num > 3999) return "";
        StringBuilder sb = new StringBuilder();
        int[] values = new int[]{1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] symbols = new String[]{"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        for (int i = 0; i < values.length; i++) {
            while (num >= values[i]) {
                num -= values[i];
                sb.append(symbols[i]);
            }
        }
        return sb.toString();
    }
}
