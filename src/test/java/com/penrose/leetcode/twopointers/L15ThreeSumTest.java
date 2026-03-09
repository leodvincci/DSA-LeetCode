package com.penrose.leetcode.twopointers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class L15ThreeSumTest {

    private L15ThreeSum solution;

    @BeforeEach
    void setUp() {
        solution = new L15ThreeSum();
    }

    @Test
    void example1() {
        List<List<Integer>> result = solution.threeSum(new int[]{-1, 0, 1, 2, -1, -4});
        assertEquals(2, result.size());
        assertTrue(result.contains(List.of(-1, -1, 2)));
        assertTrue(result.contains(List.of(-1, 0, 1)));
    }

    @Test
    void example2() {
        List<List<Integer>> result = solution.threeSum(new int[]{0, 1, 1});
        assertTrue(result.isEmpty());
    }

    @Test
    void example3() {
        List<List<Integer>> result = solution.threeSum(new int[]{0, 0, 0});
        assertEquals(1, result.size());
        assertTrue(result.contains(List.of(0, 0, 0)));
    }

    @Test
    void allZeros() {
        List<List<Integer>> result = solution.threeSum(new int[]{0, 0, 0, 0});
        assertEquals(1, result.size());
        assertTrue(result.contains(List.of(0, 0, 0)));
    }

    @Test
    void noTriplets() {
        List<List<Integer>> result = solution.threeSum(new int[]{1, 2, 3});
        assertTrue(result.isEmpty());
    }

    @Test
    void multipleTriplets() {
        List<List<Integer>> result = solution.threeSum(new int[]{-2, 0, 1, 1, 2});
        assertEquals(2, result.size());
        assertTrue(result.contains(List.of(-2, 0, 2)));
        assertTrue(result.contains(List.of(-2, 1, 1)));
    }

    @Test
    void allNegative() {
        List<List<Integer>> result = solution.threeSum(new int[]{-1, -2, -3});
        assertTrue(result.isEmpty());
    }

    @Test
    void noDuplicateTriplets() {
        List<List<Integer>> result = solution.threeSum(new int[]{-1, -1, -1, 0, 1, 1, 1});
        assertEquals(1, result.size());
        assertTrue(result.contains(List.of(-1, 0, 1)));
    }
}
