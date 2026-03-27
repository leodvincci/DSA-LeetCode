package com.penrose.leetcode.slidingwindow;

/**
 * Warmup: Maximum Sum Subarray of Size K
 * Pattern: Sliding Window (fixed-size window)
 * <p>
 * Given an integer array nums and an integer k, find the maximum sum
 * of any contiguous subarray of exactly size k.
 * <p>
 * Constraints:
 * 1 <= k <= nums.length <= 100,000
 * -10,000 <= nums[i] <= 10,000
 * <p>
 * Target: O(n) time, O(1) space
 */
public class MaxSumSubarrayOfSizeK {

    public int maxSumSubarray(int[] nums, int k) {

        int sum = 0;

        for(int i = 0; i < k; i++){
            sum+= nums[i];
        }

        int max = sum;

        for(int i = k; i < nums.length; i++){
            sum-= nums[i-k];
            sum+= nums[i];
            max = Math.max(sum,max);
            System.out.println(nums[i-k]);
        }

        return max;
    }

    /**
     * k=3
     *  0  1  2  3  4  5
     * [2, 1, 5, 1, 3, 2]
     * i3        i
     *
     *
     * 1st -> 8
     * 2nd -> 8 -2 = 6 + 1 = 7
     * 3rd -> 7 - 1 = 6
     *
     */
}
