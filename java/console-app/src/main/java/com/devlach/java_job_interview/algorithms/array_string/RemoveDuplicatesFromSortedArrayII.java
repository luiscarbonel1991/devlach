package com.devlach.java_job_interview.algorithms.array_string;

import java.util.Arrays;

public class RemoveDuplicatesFromSortedArrayII {

    /**
     * Given an integer array nums sorted in non-decreasing order, remove some duplicates in-place such that each unique element appears at most twice. The relative order of the elements should be kept the same.
     *
     * Since it is impossible to change the length of the array in some languages, you must instead have the result be placed in the first part of the array nums. More formally, if there are k elements after removing the duplicates, then the first k elements of nums should hold the final result. It does not matter what you leave beyond the first k elements.
     *
     * Return k after placing the final result in the first k slots of nums.
     *
     * Do not allocate extra space for another array. You must do this by modifying the input array in-place with O(1) extra memory.
     *
     */

    public static void main(String[] args) {
        int[] nums = new int[]{1,1,1,2,2,3};
        System.out.println("removeDuplicates(nums) = " + removeDuplicates(nums));
        System.out.println("Arrays.toString(nums) = " + Arrays.toString(nums));
    }

    public static int removeDuplicates(int[] nums) {
        int n = nums.length;
        if(n <= 2 ) return n;
        int k = 2;
        for (int i = 2; i < n; i++) {
            if(nums[i] != nums[i - 2]) {
                nums[k++] = nums[i];
            }
        }
        return k;
    }
}
