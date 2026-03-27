package com.penrose.leetcode.twopointers;

import com.penrose.leetcode.warmup.ReverseArrayInPlace;

import java.util.Arrays;

/**
 * 189. Rotate Array
 * Pattern: Two Pointers
 * <p>
 * Given an integer array nums, rotate the array to the right by k steps.
 * Must be done in-place with O(1) extra memory.
 * <p>
 * Constraints:
 * 1 <= nums.length <= 10^5
 * -2^31 <= nums[i] <= 2^31 - 1
 * 0 <= k <= 10^5
 */
public class L189RotateArray {

    public void rotate(int[] nums, int k) {

        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            left++;
            right--;
        }

        left = 0;
        //why - 1?
        right = k % nums.length - 1;

        while (left < right) {
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            left++;
            right--;
        }

        left = right = k % nums.length;
        right = nums.length - 1;

        while (left < right) {
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            left++;
            right--;
        }




    }
}
