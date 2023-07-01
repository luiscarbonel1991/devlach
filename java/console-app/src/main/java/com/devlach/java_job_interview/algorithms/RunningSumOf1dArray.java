package com.devlach.java_job_interview.algorithms;

import java.util.Arrays;

public class RunningSumOf1dArray {
    public static void main(String[] args) {
        int[] input = {1, 2, 3, 4};
        System.out.println("runningSum(input) = " + Arrays.toString(runningSum(input)));
    }

    public static int[] runningSum(int[] nums) {
        int[] results = new int[nums.length];
        results[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            results[i] = nums[i] + results[i -1];
        }
        return results;
    }
}
