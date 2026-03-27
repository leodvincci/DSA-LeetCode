package com.penrose.leetcode.slidingwindow;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MaxSumSubarrayOfSizeKTest {

    private MaxSumSubarrayOfSizeK solution;

    @BeforeEach
    void setUp() {
        solution = new MaxSumSubarrayOfSizeK();
    }

    @Test
    void example1_windowOfThree() {
        assertEquals(9, solution.maxSumSubarray(new int[]{2, 1, 5, 1, 3, 2}, 3));
    }

    @Test
    void example2_windowOfTwo() {
        assertEquals(7, solution.maxSumSubarray(new int[]{2, 3, 4, 1, 5}, 2));
    }

    @Test
    void example3_windowIsEntireArray() {
        assertEquals(5, solution.maxSumSubarray(new int[]{1, 1, 1, 1, 1}, 5));
    }

    @Test
    void example4_singleElement() {
        assertEquals(10, solution.maxSumSubarray(new int[]{10}, 1));
    }

    @Test
    void example5_allNegative() {
        assertEquals(-3, solution.maxSumSubarray(new int[]{-1, -2, -3, -4}, 2));
    }

    @Test
    void windowAtEnd() {
        assertEquals(11, solution.maxSumSubarray(new int[]{1, 2, 3, 4, 7}, 2));
    }

    @Test
    void allSameValues() {
        assertEquals(15, solution.maxSumSubarray(new int[]{5, 5, 5, 5, 5}, 3));
    }

    @Test
    void mixedPositiveAndNegative() {
        assertEquals(7, solution.maxSumSubarray(new int[]{-1, 4, -2, 5, 1, -3}, 3));
    }
}
