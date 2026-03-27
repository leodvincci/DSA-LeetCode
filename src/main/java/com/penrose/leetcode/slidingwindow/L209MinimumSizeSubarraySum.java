package com.penrose.leetcode.slidingwindow;

/**
 * 209. Minimum Size Subarray Sum
 * Pattern: Sliding Window (variable-size window)
 *
 * Given an array of positive integers nums and a positive integer target,
 * return the minimal length of a contiguous subarray whose sum is greater
 * than or equal to target. If no such subarray exists, return 0.
 *
 * Constraints:
 *   1 <= target <= 10^9
 *   1 <= nums.length <= 100,000
 *   1 <= nums[i] <= 10,000
 *
 * Target: O(n) time
 */
public class L209MinimumSizeSubarraySum {

    public int minSubArrayLen(int target, int[] nums) {
        int left = 0;
        int windowSum = 0;
        int minLength = Integer.MAX_VALUE;

        for (int right = 0; right < nums.length; right++) {
            windowSum += nums[right];

            while (windowSum >= target) {
                minLength = Math.min(minLength, right - left + 1);
                windowSum -= nums[left];
                left++;
            }
        }

        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }
}
