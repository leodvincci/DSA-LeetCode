package com.penrose.leetcode.monotonicstack;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class L155MinStackTest {

    private L155MinStack minStack;

    @BeforeEach
    void setUp() {
        minStack = new L155MinStack();
    }

    @Test
    void example1() {
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        assertEquals(-3, minStack.getMin());
        minStack.pop();
        assertEquals(0, minStack.top());
        assertEquals(-2, minStack.getMin());
    }

    @Test
    void singleElement() {
        minStack.push(42);
        assertEquals(42, minStack.top());
        assertEquals(42, minStack.getMin());
    }

    @Test
    void pushAndPopSingleElement() {
        minStack.push(5);
        minStack.push(10);
        minStack.pop();
        assertEquals(5, minStack.top());
        assertEquals(5, minStack.getMin());
    }

    @Test
    void minUpdatesAfterPop() {
        minStack.push(3);
        minStack.push(1);
        minStack.push(2);
        assertEquals(1, minStack.getMin());
        minStack.pop();
        assertEquals(1, minStack.getMin());
        minStack.pop();
        assertEquals(3, minStack.getMin());
    }

    @Test
    void duplicateMinValues() {
        minStack.push(1);
        minStack.push(1);
        minStack.push(1);
        minStack.pop();
        assertEquals(1, minStack.getMin());
        minStack.pop();
        assertEquals(1, minStack.getMin());
    }

    @Test
    void decreasingOrder() {
        minStack.push(3);
        minStack.push(2);
        minStack.push(1);
        assertEquals(1, minStack.getMin());
        minStack.pop();
        assertEquals(2, minStack.getMin());
        minStack.pop();
        assertEquals(3, minStack.getMin());
    }

    @Test
    void increasingOrder() {
        minStack.push(1);
        minStack.push(2);
        minStack.push(3);
        assertEquals(1, minStack.getMin());
        minStack.pop();
        assertEquals(1, minStack.getMin());
        minStack.pop();
        assertEquals(1, minStack.getMin());
    }

    @Test
    void negativeValues() {
        minStack.push(-1);
        minStack.push(-2);
        minStack.push(-3);
        assertEquals(-3, minStack.getMin());
        assertEquals(-3, minStack.top());
        minStack.pop();
        assertEquals(-2, minStack.getMin());
    }

    @Test
    void mixedPushPopSequence() {
        minStack.push(5);
        minStack.push(3);
        minStack.push(7);
        assertEquals(3, minStack.getMin());
        minStack.pop();
        assertEquals(3, minStack.getMin());
        minStack.pop();
        assertEquals(5, minStack.getMin());
        minStack.push(1);
        assertEquals(1, minStack.getMin());
    }

    @Test
    void largeValues() {
        minStack.push(Integer.MAX_VALUE);
        minStack.push(Integer.MIN_VALUE);
        assertEquals(Integer.MIN_VALUE, minStack.getMin());
        minStack.pop();
        assertEquals(Integer.MAX_VALUE, minStack.getMin());
    }

    @Test
    void topDoesNotRemove() {
        minStack.push(10);
        minStack.push(20);
        assertEquals(20, minStack.top());
        assertEquals(20, minStack.top());
        assertEquals(10, minStack.getMin());
    }

    @Test
    void getMinDoesNotRemove() {
        minStack.push(5);
        minStack.push(3);
        assertEquals(3, minStack.getMin());
        assertEquals(3, minStack.getMin());
        assertEquals(3, minStack.top());
    }
}
