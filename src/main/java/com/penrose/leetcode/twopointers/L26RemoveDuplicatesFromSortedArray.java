package com.penrose.leetcode.twopointers;

/**
 * 26. Remove Duplicates from Sorted Array
 * Pattern: Two Pointers
 * <p>
 * Given an integer array nums sorted in non-decreasing order, remove the
 * duplicates in-place such that each unique element appears only once.
 * Return the number of unique elements.
 * <p>
 * Constraints:
 * 1 <= nums.length <= 3 * 10^4
 * -100 <= nums[i] <= 100
 * nums is sorted in non-decreasing order.
 */
public class L26RemoveDuplicatesFromSortedArray {

    public int removeDuplicates(int[] nums) {
        int reader = 1;
        int writer = 1;

        while(reader < nums.length){
            if (nums[writer - 1] != nums[reader]) {
                nums[writer] = nums[reader];
                writer++;
            }
            reader++;
        }

        return  writer;
    }
}