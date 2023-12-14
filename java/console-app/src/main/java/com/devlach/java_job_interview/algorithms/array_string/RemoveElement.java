package com.devlach.java_job_interview.algorithms.array_string;

import java.util.Arrays;

public class RemoveElement {

    public static void main(String[] args) {
        int[] nums = {3,2,2,3};
        int val = 3;

        System.out.println("removeElement(nums, val) = " + removeElement(nums, val));
        System.out.println("nums = " + Arrays.toString(nums));
    }

    public static int removeElement(int[] nums, int val) {
        int i = 0;
        for(int j = 0; j < nums.length; j++){
            if(nums[j] != val){
                nums[i++] = nums[j];
            }
        }
        return i;
    }
}
