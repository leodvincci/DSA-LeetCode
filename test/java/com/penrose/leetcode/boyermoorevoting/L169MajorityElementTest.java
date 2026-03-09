package com.penrose.leetcode.boyermoorevoting;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class L169MajorityElementTest {

    private L169MajorityElement solution;

    @BeforeEach
    void setUp() {
        solution = new L169MajorityElement();
    }

    @Test
    void example1() {
        assertEquals(3, solution.majorityElement(new int[]{3, 2, 3}));
    }

    @Test
    void example2() {
        assertEquals(2, solution.majorityElement(new int[]{2, 2, 1, 1, 1, 2, 2}));
    }

    @Test
    void singleElement() {
        assertEquals(1, solution.majorityElement(new int[]{1}));
    }

    @Test
    void twoSameElements() {
        assertEquals(5, solution.majorityElement(new int[]{5, 5}));
    }

    @Test
    void majorityAtStart() {
        assertEquals(1, solution.majorityElement(new int[]{1, 1, 1, 2, 3}));
    }

    @Test
    void majorityAtEnd() {
        assertEquals(4, solution.majorityElement(new int[]{1, 2, 4, 4, 4}));
    }

    @Test
    void allSameElements() {
        assertEquals(7, solution.majorityElement(new int[]{7, 7, 7, 7, 7}));
    }

    @Test
    void negativeNumbers() {
        assertEquals(-1, solution.majorityElement(new int[]{-1, -1, -1, 2, 3}));
    }

    @Test
    void mixedPositiveAndNegative() {
        assertEquals(-3, solution.majorityElement(new int[]{-3, 5, -3, -3, 5}));
    }

    @Test
    void largeArray() {
        int[] nums = new int[10001];
        for (int i = 0; i < 5001; i++) {
            nums[i] = 42;
        }
        for (int i = 5001; i < 10001; i++) {
            nums[i] = 99;
        }
        assertEquals(42, solution.majorityElement(nums));
    }
}
