package com.penrose.leetcode.warmup;
/**
 * Warmup: Reverse Array In Place
 * Pattern: Two Pointers (warmup drill)
 *
 * Given an integer array nums, reverse the array in-place.
 * Do not return a new array. Modify nums directly.
 *
 * Constraints:
 * 0 <= nums.length <= 50,000
 * -100,000 <= nums[i] <= 100,000
 * Must be done in-place with O(1) extra memory.
 */
public class ReverseArrayInPlace {

    public void reverse(int[] nums) {
        int left = 0;
        int right = nums.length - 1;

        while (left < right){
            int temp = nums[right];
            nums[right] = nums[left];
            nums[left] = temp;
            left++;
            right--;
        }
    }
}
