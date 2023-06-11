package com.devlach.java_job_interview.algorithms;

import java.util.concurrent.Callable;

public class Included {

    public static final String IS_INCLUDED = "isIncluded() = ";

    public static void main(String[] args) {

        // isSubset([1, 2, 3], [1, 2]) = true
        // isSubset([1, 2, 3], [1, 2, 3, 4]) = false
        // isSubset([7,3,23,6,61,17,2,0], [61, 3, 17]) = true
        // isSubset([7,3,23,6,61,17,2,0], [17, 661, 3]) = false
        //measureTime(() -> isIncluded(new int[]{1, 2, 3}, new int[]{1, 2}));
        //measureTime(() -> isIncluded(new int[]{1, 2, 3}, new int[]{1, 2, 3, 4}));
        measureTime(() -> isIncluded(new int[]{7, 3, 23, 6, 61, 17, 2, 0, 5, 7, 8787, 78, 78, 787}, new int[]{61, 3, 17}));
        measureTime(() -> isIncluded(new int[]{7, 3, 23, 6, 61, 17, 2, 0, 5, 7, 8787, 78, 78, 787}, new int[]{17, 661, 3}));

    }

    private static void measureTime(Callable<Boolean> runnable) {
        long start = System.currentTimeMillis();
        try {
            System.out.println("runnable.call() = " + runnable.call());
            long end = System.currentTimeMillis();
            System.out.println("Time = " + (end - start));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    private static boolean isIncluded(int[] universe, int[] isSubset) {
        // sort the arrays
        sort(universe);
        sort(isSubset);
        // check if the subset is included in the universe
        int i = 0;
        int j = 0;

        while (i < universe.length && j < isSubset.length) {
            if (universe[i] == isSubset[j]) {
                i++;
                j++;
            } else {
                i++;
            }
        }
        return j == isSubset.length;
    }

    private static void sort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] > array[j]) {
                    int tmp = array[i];
                    array[i] = array[j];
                    array[j] = tmp;
                }
            }
        }
    }
}
