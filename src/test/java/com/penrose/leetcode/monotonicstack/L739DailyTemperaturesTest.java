package com.penrose.leetcode.monotonicstack;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class L739DailyTemperaturesTest {

    private L739DailyTemperatures solution;

    @BeforeEach
    void setUp() {
        solution = new L739DailyTemperatures();
    }

    @Test
    void example1() {
        assertArrayEquals(new int[]{1, 1, 4, 2, 1, 1, 0, 0},
                solution.dailyTemperatures(new int[]{73, 74, 75, 71, 69, 72, 76, 73}));
    }

    @Test
    void example2() {
        assertArrayEquals(new int[]{1, 1, 1, 0},
                solution.dailyTemperatures(new int[]{30, 40, 50, 60}));
    }

    @Test
    void example3() {
        assertArrayEquals(new int[]{1, 1, 0},
                solution.dailyTemperatures(new int[]{30, 60, 90}));
    }

    @Test
    void singleElement() {
        assertArrayEquals(new int[]{0},
                solution.dailyTemperatures(new int[]{50}));
    }

    @Test
    void allSameTemperature() {
        assertArrayEquals(new int[]{0, 0, 0, 0},
                solution.dailyTemperatures(new int[]{70, 70, 70, 70}));
    }

    @Test
    void decreasingTemperatures() {
        assertArrayEquals(new int[]{0, 0, 0, 0},
                solution.dailyTemperatures(new int[]{90, 80, 70, 60}));
    }

    @Test
    void increasingTemperatures() {
        assertArrayEquals(new int[]{1, 1, 1, 1, 0},
                solution.dailyTemperatures(new int[]{31, 32, 33, 34, 35}));
    }

    @Test
    void twoElements() {
        assertArrayEquals(new int[]{1, 0},
                solution.dailyTemperatures(new int[]{40, 50}));
    }

    @Test
    void twoElementsNoWarmer() {
        assertArrayEquals(new int[]{0, 0},
                solution.dailyTemperatures(new int[]{50, 40}));
    }

    @Test
    void warmerDayFarAway() {
        assertArrayEquals(new int[]{5, 4, 3, 2, 1, 0},
                solution.dailyTemperatures(new int[]{30, 30, 30, 30, 30, 31}));
    }

    @Test
    void valleyShape() {
        assertArrayEquals(new int[]{0, 1, 0},
                solution.dailyTemperatures(new int[]{80, 40, 50}));
    }
}
