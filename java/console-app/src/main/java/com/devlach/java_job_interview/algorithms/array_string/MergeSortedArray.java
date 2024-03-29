package com.devlach.java_job_interview.algorithms.array_string;

import java.util.Arrays;

public class MergeSortedArray {

/*
You are given two integer arrays nums1 and nums2, sorted in non-decreasing order, and two integers m and n, representing the number of elements in nums1 and nums2 respectively.

Merge nums1 and nums2 into a single array sorted in non-decreasing order.

The final sorted array should not be returned by the function, but instead be stored inside the array nums1. To accommodate this, nums1 has a length of m + n, where the first m elements denote the elements that should be merged, and the last n elements are set to 0 and should be ignored. nums2 has a length of n.
 */

    public static void main(String[] args) {
      /*  int[] nums1 = {1,};
        int[] nums2 = {1,2,3};
        merge(nums1, 1, nums2, 3);
        System.out.println("Arrays.toString(nums1) = " + Arrays.toString(nums1));*/

        int[] nums3 = new int[]{1,2,3,0,0,0};
        int[] nums4 = new int[]{2,5,6};
        merge2(nums3, 3, nums4, 3);
        System.out.println("nums3 = " + Arrays.toString(nums3));
    }
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1;
        int j = n - 1;
        int k = m + n - 1;

        while (i >= 0 && j >= 0) {
            if (nums1[i] > nums2[j]) {
                nums1[k] = nums1[i];
                i--;
            } else {
                nums1[k] = nums2[j];
                j--;
            }
            k--;
        }

        while (j >= 0) {
            nums1[k] = nums2[j];
            j--;
            k--;
        }
    }

    public static void merge2(int[] nums1, int m, int[] nums2, int n) {
        int i = m -1;
        int j = n -1;
        int merge = m + n -1;

        while( j >= 0) {
            if(i >= 0 && nums1[i] >= nums2[j]){
                nums1[merge--] = nums1[i--];
            } else {
                nums1[merge--] = nums2[j--];
            }
        }
    }
}
