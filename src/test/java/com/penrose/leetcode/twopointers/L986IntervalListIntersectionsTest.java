package com.penrose.leetcode.twopointers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class L986IntervalListIntersectionsTest {

    private L986IntervalListIntersections solution;

    @BeforeEach
    void setUp() {
        solution = new L986IntervalListIntersections();
    }

    @Test
    void example1() {
        int[][] firstList = {{0, 2}, {5, 10}, {13, 23}, {24, 25}};
        int[][] secondList = {{1, 5}, {8, 12}, {15, 24}, {25, 26}};
        int[][] expected = {{1, 2}, {5, 5}, {8, 10}, {15, 23}, {24, 24}, {25, 25}};
        assertArrayEquals(expected, solution.intervalIntersection(firstList, secondList));
    }

    @Test
    void example2_secondListEmpty() {
        int[][] firstList = {{1, 3}, {5, 9}};
        int[][] secondList = {};
        int[][] expected = {};
        assertArrayEquals(expected, solution.intervalIntersection(firstList, secondList));
    }

    @Test
    void firstListEmpty() {
        int[][] firstList = {};
        int[][] secondList = {{1, 3}, {5, 9}};
        int[][] expected = {};
        assertArrayEquals(expected, solution.intervalIntersection(firstList, secondList));
    }

    @Test
    void noOverlap() {
        int[][] firstList = {{1, 2}, {5, 6}};
        int[][] secondList = {{3, 4}, {7, 8}};
        int[][] expected = {};
        assertArrayEquals(expected, solution.intervalIntersection(firstList, secondList));
    }

    @Test
    void completeOverlap() {
        int[][] firstList = {{1, 10}};
        int[][] secondList = {{1, 10}};
        int[][] expected = {{1, 10}};
        assertArrayEquals(expected, solution.intervalIntersection(firstList, secondList));
    }

    @Test
    void singlePointIntersection() {
        int[][] firstList = {{1, 3}};
        int[][] secondList = {{3, 5}};
        int[][] expected = {{3, 3}};
        assertArrayEquals(expected, solution.intervalIntersection(firstList, secondList));
    }

    @Test
    void oneContainsAnother() {
        int[][] firstList = {{1, 10}};
        int[][] secondList = {{3, 5}};
        int[][] expected = {{3, 5}};
        assertArrayEquals(expected, solution.intervalIntersection(firstList, secondList));
    }

    @Test
    void multipleIntersectionsWithOneInterval() {
        int[][] firstList = {{0, 100}};
        int[][] secondList = {{1, 2}, {5, 10}, {20, 30}};
        int[][] expected = {{1, 2}, {5, 10}, {20, 30}};
        assertArrayEquals(expected, solution.intervalIntersection(firstList, secondList));
    }

    @Test
    void singleIntervalEach() {
        int[][] firstList = {{1, 5}};
        int[][] secondList = {{3, 7}};
        int[][] expected = {{3, 5}};
        assertArrayEquals(expected, solution.intervalIntersection(firstList, secondList));
    }
}
