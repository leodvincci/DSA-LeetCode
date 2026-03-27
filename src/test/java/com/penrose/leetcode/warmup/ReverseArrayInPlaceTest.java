package com.penrose.leetcode.warmup;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class ReverseArrayInPlaceTest {

    private ReverseArrayInPlace solution;

    @BeforeEach
    void setUp() {
        solution = new ReverseArrayInPlace();
    }

    @Test
    void example1_oddLength() {
        int[] nums = {1, 2, 3, 4, 5};
        solution.reverse(nums);
        assertArrayEquals(new int[]{5, 4, 3, 2, 1}, nums);
    }

    @Test
    void example2_twoElements() {
        int[] nums = {1, 2};
        solution.reverse(nums);
        assertArrayEquals(new int[]{2, 1}, nums);
    }

    @Test
    void singleElement() {
        int[] nums = {1};
        solution.reverse(nums);
        assertArrayEquals(new int[]{1}, nums);
    }

    @Test
    void emptyArray() {
        int[] nums = {};
        solution.reverse(nums);
        assertArrayEquals(new int[]{}, nums);
    }

    @Test
    void allIdentical() {
        int[] nums = {3, 3, 3, 3};
        solution.reverse(nums);
        assertArrayEquals(new int[]{3, 3, 3, 3}, nums);
    }

    @Test
    void evenLength() {
        int[] nums = {1, 2, 3, 4};
        solution.reverse(nums);
        assertArrayEquals(new int[]{4, 3, 2, 1}, nums);
    }

    @Test
    void negativeValues() {
        int[] nums = {-5, -3, 0, 3, 5};
        solution.reverse(nums);
        assertArrayEquals(new int[]{5, 3, 0, -3, -5}, nums);
    }

    @Test
    void alreadyPalindromic() {
        int[] nums = {1, 2, 3, 2, 1};
        solution.reverse(nums);
        assertArrayEquals(new int[]{1, 2, 3, 2, 1}, nums);
    }
}
