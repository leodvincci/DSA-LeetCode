package com.penrose.leetcode.slidingwindow;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class L395LongestSubstringKRepeatingTest {

    private L395LongestSubstringKRepeating solution;

    @BeforeEach
    void setUp() {
        solution = new L395LongestSubstringKRepeating();
    }

    @Test
    void example1() {
        // "aaa" is the longest substring where every char appears >= 3 times
        assertEquals(3, solution.longestSubstring("aaabb", 3));
    }

    @Test
    void example2() {
        // "ababb" -> 'a' appears 2 times, 'b' appears 3 times, both >= 2
        assertEquals(5, solution.longestSubstring("ababbc", 2));
    }

    @Test
    void singleCharRepeated() {
        assertEquals(5, solution.longestSubstring("aaaaa", 1));
    }

    @Test
    void kGreaterThanStringLength() {
        // k=6 but string length is 5, impossible for any char to appear 6 times
        assertEquals(0, solution.longestSubstring("aaabb", 6));
    }

    @Test
    void allCharsMeetK() {
        // Entire string is valid: a=3, b=3
        assertEquals(6, solution.longestSubstring("aaabbb", 3));
    }

    @Test
    void singleCharString() {
        assertEquals(1, solution.longestSubstring("a", 1));
    }

    @Test
    void singleCharStringKTooLarge() {
        assertEquals(0, solution.longestSubstring("a", 2));
    }

    @Test
    void noValidSubstring() {
        // Every character appears only once
        assertEquals(0, solution.longestSubstring("abcdef", 2));
    }

    @Test
    void multipleValidSubstrings() {
        // "aaa" (len 3) and "bb" are not valid for k=2 combined,
        // but "aaa" has all chars >= 2, so length 3
        assertEquals(3, solution.longestSubstring("aaabcbb", 2));
    }

    @Test
    void entireStringValid() {
        // a=2, b=2, both >= 2
        assertEquals(4, solution.longestSubstring("aabb", 2));
    }

    @Test
    void kEqualsOne() {
        // Every substring is valid when k=1, so the whole string
        assertEquals(7, solution.longestSubstring("abcdefg", 1));
    }
}
