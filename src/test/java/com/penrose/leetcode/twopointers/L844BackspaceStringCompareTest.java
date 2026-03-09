package com.penrose.leetcode.twopointers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class L844BackspaceStringCompareTest {

    private L844BackspaceStringCompare solution;

    @BeforeEach
    void setUp() {
        solution = new L844BackspaceStringCompare();
    }

    @Test
    void example1() {
        assertTrue(solution.backspaceCompare("ab#c", "ad#c"));
    }

    @Test
    void example2() {
        assertTrue(solution.backspaceCompare("ab##", "c#d#"));
    }

    @Test
    void example3() {
        assertFalse(solution.backspaceCompare("a#c", "b"));
    }

    @Test
    void noBackspaces() {
        assertTrue(solution.backspaceCompare("abc", "abc"));
    }

    @Test
    void allBackspaced() {
        assertTrue(solution.backspaceCompare("a#", "b#"));
    }

    @Test
    void backspaceOnEmpty() {
        assertTrue(solution.backspaceCompare("##", "#"));
    }

    @Test
    void multipleBackspaces() {
        assertTrue(solution.backspaceCompare("abc###", ""));
    }

    @Test
    void differentLengthsSameResult() {
        assertTrue(solution.backspaceCompare("bxj##tw", "bxo#j##tw"));
    }

    @Test
    void singleCharMismatch() {
        assertFalse(solution.backspaceCompare("a", "b"));
    }

    @Test
    void singleCharMatch() {
        assertTrue(solution.backspaceCompare("a", "a"));
    }
}
