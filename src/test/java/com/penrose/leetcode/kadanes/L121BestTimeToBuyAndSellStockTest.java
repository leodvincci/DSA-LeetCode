package com.penrose.leetcode.kadanes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class L121BestTimeToBuyAndSellStockTest {

    private L121BestTimeToBuyAndSellStock solution;

    @BeforeEach
    void setUp() {
        solution = new L121BestTimeToBuyAndSellStock();
    }

    @Test
    void example1() {
        assertEquals(5, solution.maxProfit(new int[]{7, 1, 5, 3, 6, 4}));
    }

    @Test
    void example2() {
        assertEquals(0, solution.maxProfit(new int[]{7, 6, 4, 3, 1}));
    }

    @Test
    void singleDay() {
        assertEquals(0, solution.maxProfit(new int[]{5}));
    }

    @Test
    void strictlyIncreasing() {
        assertEquals(3, solution.maxProfit(new int[]{1, 2, 3, 4}));
    }

    @Test
    void buyLowSellHighMidArray() {
        assertEquals(2, solution.maxProfit(new int[]{2, 4, 1}));
    }

    @Test
    void twoDaysProfit() {
        assertEquals(4, solution.maxProfit(new int[]{1, 5}));
    }

    @Test
    void twoDaysNoProfit() {
        assertEquals(0, solution.maxProfit(new int[]{5, 1}));
    }
}
