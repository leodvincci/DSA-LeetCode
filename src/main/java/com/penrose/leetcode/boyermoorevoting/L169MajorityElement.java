package com.penrose.leetcode.boyermoorevoting;

/**
 * 169. Majority Element
 * <p>
 * Given an array nums of size n, return the majority element.
 * <p>
 * The majority element is the element that appears more than ⌊n / 2⌋ times.
 * You may assume that the majority element always exists in the array.
 * <p>
 * Constraints:
 * n == nums.length
 * 1 <= n <= 5 * 10^4
 * -10^9 <= nums[i] <= 10^9
 * <p>
 * Follow-up: Could you solve the problem in linear time and in O(1) space?
 */
public class L169MajorityElement {

    public int majorityElement(int[] nums) {
        // TODO: implement your solution here
        int candidate = 0;
        int votes = 0;

        for (int number : nums) {

            if (votes == 0) {
                candidate = number;
            }

            if (number == candidate) {
                votes++;
            } else {
                votes--;
            }

        }

        return candidate;
    }
}
