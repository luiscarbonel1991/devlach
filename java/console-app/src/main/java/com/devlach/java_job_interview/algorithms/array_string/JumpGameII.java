package com.devlach.java_job_interview.algorithms.array_string;

public class JumpGameII {

    /**
     *
     * You are given a 0-indexed array of integers nums of length n. You are initially positioned at nums[0].
     *
     * Each element nums[i] represents the maximum length of a forward jump from index i. In other words, if you are at nums[i], you can jump to any nums[i + j] where:
     *
     * 0 <= j <= nums[i] and
     * i + j < n
     * Return the minimum number of jumps to reach nums[n - 1]. The test cases are generated such that you can reach nums[n - 1].
     *
     *
     *
     * Example 1:
     *
     * Input: nums = [2,3,1,1,4]
     * Output: 2
     * Explanation: The minimum number of jumps to reach the last index is 2. Jump 1 step from index 0 to 1, then 3 steps to the last index.
     * Example 2:
     *
     * Input: nums = [2,3,0,1,4]
     * Output: 2
     *
     *
     * Constraints:
     *
     * 1 <= nums.length <= 104
     * 0 <= nums[i] <= 1000
     * It's guaranteed that you can reach nums[n - 1].
     */
    public static void main(String[] args) {
        System.out.println("canJump(new int[]{2,3,1,1,4}) = " + canJump(new int[]{2,3,1,1,4}));
        System.out.println("canJump(new int[]{2,3,0,1,4}) = " + canJump(new int[]{2,3,0,1,4}));
    }


    public static int canJump(int[] nums){
        if (nums.length <= 1) return 0;

        int jumps = 0;
        int maxReach = nums[0];
        int currentJumpMax = nums[0];

        for (int i = 1; i < nums.length; i++) {
            if(i > currentJumpMax) {
                jumps++;
                currentJumpMax = maxReach;
            }
           maxReach = Math.max(maxReach, i + nums[i]);
        }

        return jumps + 1;
    }
}
