package com.devlach.java_job_interview.algorithms.hasmap;

import java.util.Arrays;
import java.util.HashMap;

public class TwoSum {

    public static void main(String[] args) {
        var test1 = new int[]{2,7,11,15};
        var target1 = 9;
        System.out.println("Arrays.toString(twoSum(test1, target1)) = " + Arrays.toString(twoSum(test1, target1)));

        var test2 = new int[]{3,2,4};
        var target2 = 6;
        System.out.println("Arrays.toString(twoSum(test1, target1)) = " + Arrays.toString(twoSum(test2, target2)));

    }

    public static int[] twoSum(int[] nums, int target){
        var map = new HashMap<Integer, Integer>();
        for(int i = 0; i < nums.length; i++){
            var rest = target - nums[i];
            if(map.containsKey(rest)) {
                var index = map.get(rest);
                return new int[]{index, i};
            }
            map.put(nums[i], i);
        }
        return new int[0];
    }
}
