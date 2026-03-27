package com.penrose.leetcode.twopointers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class L189RotateArrayTest {

    private L189RotateArray solution;

    @BeforeEach
    void setUp() {
        solution = new L189RotateArray();
    }

    @Test
    void example1_rotateByThree() {
        int[] nums = {1, 2, 3, 4, 5, 6, 7};
        solution.rotate(nums, 3);
        assertArrayEquals(new int[]{5, 6, 7, 1, 2, 3, 4}, nums);
    }

    @Test
    void example2_rotateByTwo() {
        int[] nums = {-1, -100, 3, 99};
        solution.rotate(nums, 2);
        assertArrayEquals(new int[]{3, 99, -1, -100}, nums);
    }

    @Test
    void zeroRotation() {
        int[] nums = {1, 2, 3};
        solution.rotate(nums, 0);
        assertArrayEquals(new int[]{1, 2, 3}, nums);
    }

    @Test
    void kEqualsLength_fullRotation() {
        int[] nums = {1, 2, 3};
        solution.rotate(nums, 3);
        assertArrayEquals(new int[]{1, 2, 3}, nums);
    }

    @Test
    void kGreaterThanLength() {
        int[] nums = {1, 2, 3};
        solution.rotate(nums, 5);
        assertArrayEquals(new int[]{2, 3, 1}, nums);
    }

    @Test
    void singleElement() {
        int[] nums = {1};
        solution.rotate(nums, 7);
        assertArrayEquals(new int[]{1}, nums);
    }

    @Test
    void twoElements_rotateByOne() {
        int[] nums = {1, 2};
        solution.rotate(nums, 1);
        assertArrayEquals(new int[]{2, 1}, nums);
    }

    @Test
    void rotateByOne() {
        int[] nums = {1, 2, 3, 4, 5};
        solution.rotate(nums, 1);
        assertArrayEquals(new int[]{5, 1, 2, 3, 4}, nums);
    }
}
