package com.penrose.leetcode.warmup;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CountIntervalOverlapsTest {

    private CountIntervalOverlaps solution;

    @BeforeEach
    void setUp() {
        solution = new CountIntervalOverlaps();
    }

    @Test
    void basicOverlaps() {
        assertEquals(2, solution.countOverlaps(
                new int[][]{{0, 3}, {5, 8}},
                new int[][]{{1, 4}, {6, 7}}
        ));
    }

    @Test
    void oneIntervalOverlapsMultiple() {
        assertEquals(2, solution.countOverlaps(
                new int[][]{{1, 5}},
                new int[][]{{2, 3}, {4, 6}}
        ));
    }

    @Test
    void noOverlaps() {
        assertEquals(0, solution.countOverlaps(
                new int[][]{{1, 2}, {5, 6}},
                new int[][]{{3, 4}, {7, 8}}
        ));
    }

    @Test
    void singlePointTouch() {
        assertEquals(1, solution.countOverlaps(
                new int[][]{{1, 3}},
                new int[][]{{3, 5}}
        ));
    }

    @Test
    void emptySecondList() {
        assertEquals(0, solution.countOverlaps(
                new int[][]{{1, 5}},
                new int[][]{}
        ));
    }

    @Test
    void bothEmpty() {
        assertEquals(0, solution.countOverlaps(
                new int[][]{},
                new int[][]{}
        ));
    }
}
