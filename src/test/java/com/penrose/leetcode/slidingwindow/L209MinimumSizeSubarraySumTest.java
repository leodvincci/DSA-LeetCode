package com.penrose.leetcode.slidingwindow;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class L209MinimumSizeSubarraySumTest {

    private L209MinimumSizeSubarraySum solution;

    @BeforeEach
    void setUp() {
        solution = new L209MinimumSizeSubarraySum();
    }

    @Test
    void example1_shortestWindowInMiddle() {
        assertEquals(2, solution.minSubArrayLen(7, new int[]{2, 3, 1, 2, 4, 3}));
    }

    @Test
    void example2_singleElementMeetsTarget() {
        assertEquals(1, solution.minSubArrayLen(4, new int[]{1, 4, 4}));
    }

    @Test
    void example3_noValidSubarray() {
        assertEquals(0, solution.minSubArrayLen(11, new int[]{1, 1, 1, 1, 1, 1, 1, 1}));
    }

    @Test
    void example4_entireArrayRequired() {
        assertEquals(5, solution.minSubArrayLen(15, new int[]{1, 2, 3, 4, 5}));
    }

    @Test
    void example5_singleElementExceedsTarget() {
        assertEquals(1, solution.minSubArrayLen(3, new int[]{5}));
    }

    @Test
    void targetExactlyMatchesSingleElement() {
        assertEquals(1, solution.minSubArrayLen(5, new int[]{1, 2, 5, 1, 3}));
    }

    @Test
    void allElementsEqualTarget() {
        assertEquals(1, solution.minSubArrayLen(3, new int[]{3, 3, 3, 3}));
    }

    @Test
    void windowAtVeryEnd() {
        assertEquals(2, solution.minSubArrayLen(9, new int[]{1, 1, 1, 1, 4, 5}));
    }
}
