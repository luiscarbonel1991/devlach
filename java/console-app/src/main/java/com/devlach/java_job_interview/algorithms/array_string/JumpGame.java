package com.devlach.java_job_interview.algorithms.array_string;

public class JumpGame {

    public static void main(String[] args) {
        System.out.println("canJump(new int[]{3,2,1,0,4}) = " + canJump(new int[]{3,2,1,0,4}));
        System.out.println("canJump(new int[]{2,3,1,1,4}) = " + canJump(new int[]{2,3,1,1,4}));
        System.out.println("canJump(new int[]{2,3,1,1}) = " + canJump(new int[]{2,3,1,1}));
        System.out.println("canJump(new int[]{0,1}) = " + canJump(new int[]{0,1}));
    }

    public static boolean canJump(int[] nums){
        if(nums.length == 0) return false;
        int maxReach = 0;

        for (int i = 0; i < nums.length; i++) {
            if(maxReach < i ) return false;
            maxReach = Math.max(maxReach, i + nums[i]);
            if(maxReach >= nums.length - 1) return true;
        }
        return false;
    }
}
