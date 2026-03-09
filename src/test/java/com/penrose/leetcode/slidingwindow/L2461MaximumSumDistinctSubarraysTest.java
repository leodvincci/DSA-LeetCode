package com.penrose.leetcode.slidingwindow;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class L2461MaximumSumDistinctSubarraysTest {

    private L2461MaximumSumDistinctSubarrays solution;

    @BeforeEach
    void setUp() {
        solution = new L2461MaximumSumDistinctSubarrays();
    }

    @Test
    void example1() {
        // [4,2,9] has the max sum of 15 among distinct subarrays of length 3
        assertEquals(15, solution.maximumSubarraySum(new int[]{1, 5, 4, 2, 9, 9, 9}, 3));
    }

    @Test
    void example2() {
        // All elements are the same, no distinct subarray of length 3 exists
        assertEquals(0, solution.maximumSubarraySum(new int[]{4, 4, 4}, 3));
    }

    @Test
    void singleElement() {
        // k=1, every element is a valid distinct subarray
        assertEquals(7, solution.maximumSubarraySum(new int[]{3, 7, 1}, 1));
    }

    @Test
    void entireArrayIsSubarray() {
        // k equals array length and all elements are distinct
        assertEquals(10, solution.maximumSubarraySum(new int[]{1, 2, 3, 4}, 4));
    }

    @Test
    void entireArrayHasDuplicate() {
        // k equals array length but elements are not all distinct
        assertEquals(0, solution.maximumSubarraySum(new int[]{1, 2, 2, 4}, 4));
    }

    @Test
    void duplicateAtWindowBoundary() {
        // Duplicate sits right at old/new window boundary
        assertEquals(12, solution.maximumSubarraySum(new int[]{1, 1, 5, 7}, 2));
    }

    @Test
    void allDistinctLargeValues() {
        // Verifies long return type with larger values
        assertEquals(100000, solution.maximumSubarraySum(new int[]{100000, 100000, 100000}, 1));
    }

    @Test
    void slidingWindowClearsCorrectly() {
        // Best window [3,4,5] = 12
        assertEquals(12, solution.maximumSubarraySum(new int[]{1, 2, 3, 4, 5}, 3));
    }
}
