package com.devlach.multithreating;

public class MinMaxMetrics {
    // Add all necessary member variables

    /**
     * Initializes all member variables
     */

    private volatile long min = Long.MAX_VALUE;
    private volatile long maxValue = Long.MIN_VALUE;

    public MinMaxMetrics() {
        // Add code here
    }

    /**
     * Adds a new sample to our metrics.
     */
    public  void addSample(long newSample) {
        // Add code here
        synchronized (this) {
            if (newSample >= maxValue) {
                maxValue = newSample;
            }
            if (newSample <= min) {
                min = newSample;
            }
        }
    }

    /**
     * Returns the smallest sample we've seen so far.
     */
    public long getMin() {
        // Add code here
        return this.min;
    }

    /**
     * Returns the biggest sample we've seen so far.
     */
    public long getMax() {
        // Add code here
        return this.maxValue;
    }
}
