package com.penrose.leetcode.fastandslowpointers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class L234PalindromeLinkedListTest {

    private L234PalindromeLinkedList solution;

    @BeforeEach
    void setUp() {
        solution = new L234PalindromeLinkedList();
    }

    @Test
    void example1_evenPalindrome() {
        ListNode head = buildList(1, 2, 2, 1);
        assertTrue(solution.isPalindrome(head));
    }

    @Test
    void example2_notPalindrome() {
        ListNode head = buildList(1, 2);
        assertFalse(solution.isPalindrome(head));
    }

    @Test
    void singleElement() {
        ListNode head = buildList(1);
        assertTrue(solution.isPalindrome(head));
    }

    @Test
    void oddPalindrome() {
        ListNode head = buildList(1, 2, 1);
        assertTrue(solution.isPalindrome(head));
    }

    @Test
    void longerPalindrome() {
        ListNode head = buildList(1, 2, 3, 2, 1);
        assertTrue(solution.isPalindrome(head));
    }

    @Test
    void allSameValues() {
        ListNode head = buildList(1, 1, 1, 1);
        assertTrue(solution.isPalindrome(head));
    }

    @Test
    void notPalindrome_threeElements() {
        ListNode head = buildList(1, 2, 3);
        assertFalse(solution.isPalindrome(head));
    }

    @Test
    void twoElementPalindrome() {
        ListNode head = buildList(5, 5);
        assertTrue(solution.isPalindrome(head));
    }

    private ListNode buildList(int... vals) {
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        for (int val : vals) {
            current.next = new ListNode(val);
            current = current.next;
        }
        return dummy.next;
    }
}
