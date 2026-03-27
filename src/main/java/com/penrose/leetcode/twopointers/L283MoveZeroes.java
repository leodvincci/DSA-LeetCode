package com.penrose.leetcode.twopointers;

/**
 * 283. Move Zeroes
 * Pattern: Two Pointers
 * <p>
 * Given an integer array nums, move all 0's to the end of the array
 * while maintaining the relative order of the non-zero elements.
 * You must do this in-place without making a copy of the array.
 * <p>
 * Constraints:
 * 1 <= nums.length <= 10^4
 * -2^31 <= nums[i] <= 2^31 - 1
 */
public class L283MoveZeroes {

    public void moveZeroes(int[] nums) {
        int writer = 0;
        int reader = 0;

        while(reader < nums.length){
            if(nums[reader] != 0){
                int temp = nums[writer];
                nums[writer] = nums[reader];
                nums[reader] = temp;
                writer++;
            }
            reader++;
        }
    }
}
