package com.penrose.leetcode.monotonicstack;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class L20ValidParenthesesTest {

    private L20ValidParentheses solution;

    @BeforeEach
    void setUp() {
        solution = new L20ValidParentheses();
    }

    @Test
    void example1() {
        assertTrue(solution.isValid("()"));
    }

    @Test
    void example2() {
        assertTrue(solution.isValid("()[]{}"));
    }

    @Test
    void example3() {
        assertFalse(solution.isValid("(]"));
    }

    @Test
    void example4() {
        assertTrue(solution.isValid("([])"));
    }

    @Test
    void example5() {
        assertFalse(solution.isValid("([)]"));
    }

    @Test
    void singleOpenBracket() {
        assertFalse(solution.isValid("("));
    }

    @Test
    void singleCloseBracket() {
        assertFalse(solution.isValid(")"));
    }

    @Test
    void deeplyNested() {
        assertTrue(solution.isValid("({[]})"));
    }

    @Test
    void repeatedPairs() {
        assertTrue(solution.isValid("()()()"));
    }

    @Test
    void closingWithoutOpening() {
        assertFalse(solution.isValid("())"));
    }

    @Test
    void openingWithoutClosing() {
        assertFalse(solution.isValid("(()"));
    }

    @Test
    void mismatchedTypes() {
        assertFalse(solution.isValid("{)"));
    }

    @Test
    void oddLength() {
        assertFalse(solution.isValid("({}"));
    }

    @Test
    void allOpenBrackets() {
        assertFalse(solution.isValid("((("));
    }

    @Test
    void allCloseBrackets() {
        assertFalse(solution.isValid(")))"));
    }

    @Test
    void complexValid() {
        assertTrue(solution.isValid("{[()()]}[]"));
    }

    @Test
    void reversedOrder() {
        assertFalse(solution.isValid("}{"));
    }
}
