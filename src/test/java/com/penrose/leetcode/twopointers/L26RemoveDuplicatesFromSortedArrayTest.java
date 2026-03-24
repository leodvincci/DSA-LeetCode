package com.penrose.leetcode.twopointers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class L26RemoveDuplicatesFromSortedArrayTest {

    private L26RemoveDuplicatesFromSortedArray solution;

    @BeforeEach
    void setUp() {
        solution = new L26RemoveDuplicatesFromSortedArray();
    }

    @Test
    void example1_simpleDuplicates() {
        int[] nums = {1, 1, 2};
        int k = solution.removeDuplicates(nums);
        assertEquals(2, k);
        assertArrayEquals(new int[]{1, 2}, Arrays.copyOf(nums, k));
    }

    @Test
    void example2_multipleDuplicates() {
        int[] nums = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        int k = solution.removeDuplicates(nums);
        assertEquals(5, k);
        assertArrayEquals(new int[]{0, 1, 2, 3, 4}, Arrays.copyOf(nums, k));
    }

    @Test
    void singleElement() {
        int[] nums = {1};
        int k = solution.removeDuplicates(nums);
        assertEquals(1, k);
        assertArrayEquals(new int[]{1}, Arrays.copyOf(nums, k));
    }

    @Test
    void allDuplicates() {
        int[] nums = {7, 7, 7, 7};
        int k = solution.removeDuplicates(nums);
        assertEquals(1, k);
        assertArrayEquals(new int[]{7}, Arrays.copyOf(nums, k));
    }

    @Test
    void alreadyUnique() {
        int[] nums = {1, 2, 3, 4, 5};
        int k = solution.removeDuplicates(nums);
        assertEquals(5, k);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, Arrays.copyOf(nums, k));
    }

    @Test
    void twoElements_noDuplicates() {
        int[] nums = {1, 2};
        int k = solution.removeDuplicates(nums);
        assertEquals(2, k);
        assertArrayEquals(new int[]{1, 2}, Arrays.copyOf(nums, k));
    }

    @Test
    void twoElements_duplicates() {
        int[] nums = {3, 3};
        int k = solution.removeDuplicates(nums);
        assertEquals(1, k);
        assertArrayEquals(new int[]{3}, Arrays.copyOf(nums, k));
    }

    @Test
    void negativeValues() {
        int[] nums = {-3, -1, 0, 0, 2, 2};
        int k = solution.removeDuplicates(nums);
        assertEquals(4, k);
        assertArrayEquals(new int[]{-3, -1, 0, 2}, Arrays.copyOf(nums, k));
    }
}
