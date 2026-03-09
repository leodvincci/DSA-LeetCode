package com.penrose.leetcode.twopointers;

/**
 * 977. Squares of a Sorted Array
 * <p>
 * Given an integer array nums sorted in non-decreasing order, return an array
 * of the squares of each number sorted in non-decreasing order.
 * <p>
 * Constraints:
 * 1 <= nums.length <= 10^4
 * -10^4 <= nums[i] <= 10^4
 * nums is sorted in non-decreasing order.
 */
public class L977SquaresOfSortedArray {

    public int[] sortedSquares(int[] nums) {
        // TODO: implement your solution here
        int[] res = new int[nums.length];

        int leftReader = 0;
        int rightReader = nums.length - 1;

        int writer = res.length - 1;

        while (leftReader <= rightReader) {

            if (Math.abs(nums[rightReader]) > Math.abs(nums[leftReader])) {
                res[writer] = nums[rightReader] * nums[rightReader];
                rightReader--;
            } else {
                res[writer] = nums[leftReader] * nums[leftReader];
                leftReader++;
            }
            writer--;
        }

        return res;
    }
}