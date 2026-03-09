package com.penrose.leetcode.twopointers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class L977SquaresOfSortedArrayTest {

    private L977SquaresOfSortedArray solution;

    @BeforeEach
    void setUp() {
        solution = new L977SquaresOfSortedArray();
    }

    @Test
    void example1() {
        assertArrayEquals(new int[]{0, 1, 9, 16, 100}, solution.sortedSquares(new int[]{-4, -1, 0, 3, 10}));
    }

    @Test
    void example2() {
        assertArrayEquals(new int[]{4, 9, 9, 49, 121}, solution.sortedSquares(new int[]{-7, -3, 2, 3, 11}));
    }

    @Test
    void allNegative() {
        assertArrayEquals(new int[]{1, 4, 9}, solution.sortedSquares(new int[]{-3, -2, -1}));
    }

    @Test
    void allPositive() {
        assertArrayEquals(new int[]{1, 4, 9}, solution.sortedSquares(new int[]{1, 2, 3}));
    }

    @Test
    void singleElement() {
        assertArrayEquals(new int[]{25}, solution.sortedSquares(new int[]{-5}));
    }

    @Test
    void singleZero() {
        assertArrayEquals(new int[]{0}, solution.sortedSquares(new int[]{0}));
    }

    @Test
    void withZero() {
        assertArrayEquals(new int[]{0, 1, 4}, solution.sortedSquares(new int[]{-2, -1, 0}));
    }

    @Test
    void duplicateAbsoluteValues() {
        assertArrayEquals(new int[]{1, 1, 4, 4}, solution.sortedSquares(new int[]{-2, -1, 1, 2}));
    }

    @Test
    void largeNegativeSmallPositive() {
        assertArrayEquals(new int[]{1, 100, 10000}, solution.sortedSquares(new int[]{-100, -10, 1}));
    }
}