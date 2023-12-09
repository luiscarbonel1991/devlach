package com.devlach.java_job_interview.algorithms;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BestTimeToBuyAndSellStockII {

    public static void main(String[] args) {
        int profit = maxProfit(new int[]{7,1,5,3,6,4});
        int profit1 = maxProfit(new int[]{1,2,3,4,5});
        System.out.println("profit = " + profit);
        System.out.println("profit = " + profit1);

        int profit3 = maxProfit2(new int[]{7,1,5,3,6,4});
        int profit4 = maxProfit2(new int[]{1,2,3,4,5});
        System.out.println("profit = " + profit3);
        System.out.println("profit = " + profit4);

        System.out.println("reverseWords(\"the sky is blue\") = " + reverseWords("the sky is blue"));
        System.out.println("reverseWords(\"the sky is blue\") = " + reverseWords2("the sky is blue"));
        System.out.println("reverseWords(\"  hello world  \") = " + reverseWords2(" hello world "));
    }

    /*
    You are given an integer array prices where prices[i] is the price of a given stock on the ith day.

    On each day, you may decide to buy and/or sell the stock.
    You can only hold at most one share of the stock at any time.
    However, you can buy it then immediately sell it on the same day.

    Find and return the maximum profit you can achieve.

    Example 1:

    Input: prices = [7,1,5,3,6,4]
    Output: 7
    Explanation: Buy on day 2 (price = 1) and sell on day 3 (price = 5), profit = 5-1 = 4.
    Then buy on day 4 (price = 3) and sell on day 5 (price = 6), profit = 6-3 = 3.
    Total profit is 4 + 3 = 7.

    Example 2:

    Input: prices = [1,2,3,4,5]
    Output: 4
    Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
    Total profit is 4.

    Example 3:

    Input: prices = [7,6,4,3,1]
    Output: 0
    Explanation: There is no way to make a positive profit, so we never buy the stock to achieve the maximum profit of 0.
     */
    public static int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) return 0;
        int minPrice = Integer.MAX_VALUE;
        int profit = 0;


        for (int currentPrice : prices) {
            if (minPrice < currentPrice) {
                profit += currentPrice - minPrice;
            }
            minPrice = currentPrice;
        }

        return profit;
    }

    public static int maxProfit2(int[] prices) {
        if (prices == null || prices.length < 2) return 0;

        int totalProfit = 0;

        for (int i = 0; i < prices.length - 1; i++) {
            if (prices[i + 1] > prices[i]) {
                totalProfit += prices[i + 1] - prices[i];
            }
        }

        return totalProfit;
    }

    public static String reverseWords(String s) {
        if(s == null || s.isEmpty() || s.length() > 10000) return "";
        String space = " ";
        List<String> toReverse = Arrays.asList(s.split(space));
        Collections.reverse(toReverse);
        return toReverse.stream()
                .filter(e -> !e.isBlank())
                .collect(Collectors.joining(space));
    }

    public static String reverseWords2(String s) {
        if(s == null || s.trim().isEmpty() || s.length() > 10000) return "";
        String space = " ";
        String[] sArray = s.split(space);
        StringBuilder builder = new StringBuilder();
        for (int i = sArray.length -1; i >= 0 ; i--) {
            if(!sArray[i].isEmpty() && !sArray[i].isBlank()){
                if(i == sArray.length - 1) {
                    builder.append(sArray[i]);
                } else  builder.append(space).append(sArray[i]);
            }
        }
        return builder.toString();
    }

    public static String reverseWords3(String s) {
        if(s == null || s.trim().isEmpty()) return "";

        String[] words = s.split("\\s+");
        StringBuilder reverse = new StringBuilder();
        for (int i = words.length -1; i >= 0 ; i--) {
           if(!words[i].isEmpty()){
               reverse.append(words[i]).append(" ");
           }
        }

        return reverse.toString().trim();
    }
    public static String reverseWords4(String s) {
        if(s == null || s.trim().isEmpty()) return "";
        String[] words = s.trim().split("\\s+");
        StringBuilder reverse = new StringBuilder();
        for (int i = words.length -1; i > 0 ; i--) {
            reverse.append(words[i]).append(" ");
        }

        return reverse.append(words[0]).toString();
    }

}














