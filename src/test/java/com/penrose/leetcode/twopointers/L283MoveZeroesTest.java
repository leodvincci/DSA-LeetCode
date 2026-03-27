package com.penrose.leetcode.twopointers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class L283MoveZeroesTest {

    private L283MoveZeroes solution;

    @BeforeEach
    void setUp() {
        solution = new L283MoveZeroes();
    }

    @Test
    void example1_mixedZeroesAndNonZeroes() {
        int[] nums = {0, 1, 0, 3, 12};
        solution.moveZeroes(nums);
        assertArrayEquals(new int[]{1, 3, 12, 0, 0}, nums);
    }

    @Test
    void example2_singleZero() {
        int[] nums = {0};
        solution.moveZeroes(nums);
        assertArrayEquals(new int[]{0}, nums);
    }

    @Test
    void noZeroes() {
        int[] nums = {1, 2, 3};
        solution.moveZeroes(nums);
        assertArrayEquals(new int[]{1, 2, 3}, nums);
    }

    @Test
    void allZeroes() {
        int[] nums = {0, 0, 0, 0};
        solution.moveZeroes(nums);
        assertArrayEquals(new int[]{0, 0, 0, 0}, nums);
    }

    @Test
    void zeroesAtFront_nonZeroAtEnd() {
        int[] nums = {0, 0, 1};
        solution.moveZeroes(nums);
        assertArrayEquals(new int[]{1, 0, 0}, nums);
    }

    @Test
    void singleNonZero() {
        int[] nums = {5};
        solution.moveZeroes(nums);
        assertArrayEquals(new int[]{5}, nums);
    }

    @Test
    void zeroAtEnd() {
        int[] nums = {1, 2, 0};
        solution.moveZeroes(nums);
        assertArrayEquals(new int[]{1, 2, 0}, nums);
    }

    @Test
    void alternatingZeroes() {
        int[] nums = {0, 1, 0, 2, 0, 3};
        solution.moveZeroes(nums);
        assertArrayEquals(new int[]{1, 2, 3, 0, 0, 0}, nums);
    }

    @Test
    void negativeValues() {
        int[] nums = {0, -1, 0, -3, 12};
        solution.moveZeroes(nums);
        assertArrayEquals(new int[]{-1, -3, 12, 0, 0}, nums);
    }
}
