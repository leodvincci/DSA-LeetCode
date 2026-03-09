package com.penrose.leetcode.kadanes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class L53MaximumSubarrayTest {

    private L53MaximumSubarray solution;

    @BeforeEach
    void setUp() {
        solution = new L53MaximumSubarray();
    }

    @Test
    void example1() {
        assertEquals(6, solution.maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));
    }

    @Test
    void example2() {
        assertEquals(1, solution.maxSubArray(new int[]{1}));
    }

    @Test
    void example3() {
        assertEquals(23, solution.maxSubArray(new int[]{5, 4, -1, 7, 8}));
    }

    @Test
    void allNegative() {
        assertEquals(-1, solution.maxSubArray(new int[]{-3, -2, -1, -4}));
    }

    @Test
    void allPositive() {
        assertEquals(10, solution.maxSubArray(new int[]{1, 2, 3, 4}));
    }

    @Test
    void singleNegative() {
        assertEquals(-5, solution.maxSubArray(new int[]{-5}));
    }

    @Test
    void negativesThenPositive() {
        assertEquals(5, solution.maxSubArray(new int[]{-1, -2, 5}));
    }

    @Test
    void positiveThenNegatives() {
        assertEquals(3, solution.maxSubArray(new int[]{3, -5, -2}));
    }

    @Test
    void mixedWithLargeNegativeInMiddle() {
        assertEquals(7, solution.maxSubArray(new int[]{2, -1, 3, -100, 4, 3}));
    }

    @Test
    void twoElements() {
        assertEquals(3, solution.maxSubArray(new int[]{1, 2}));
    }
}
