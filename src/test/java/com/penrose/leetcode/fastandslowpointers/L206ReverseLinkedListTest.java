package com.penrose.leetcode.fastandslowpointers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class L206ReverseLinkedListTest {

    private L206ReverseLinkedList solution;

    @BeforeEach
    void setUp() {
        solution = new L206ReverseLinkedList();
    }

    @Test
    void example1_fiveElements() {
        ListNode head = buildList(1, 2, 3, 4, 5);
        assertListEquals(new int[]{5, 4, 3, 2, 1}, solution.reverseList(head));
    }

    @Test
    void example2_twoElements() {
        ListNode head = buildList(1, 2);
        assertListEquals(new int[]{2, 1}, solution.reverseList(head));
    }

    @Test
    void example3_emptyList() {
        assertNull(solution.reverseList(null));
    }

    @Test
    void singleElement() {
        ListNode head = buildList(7);
        assertListEquals(new int[]{7}, solution.reverseList(head));
    }

    @Test
    void threeElements() {
        ListNode head = buildList(1, 2, 3);
        assertListEquals(new int[]{3, 2, 1}, solution.reverseList(head));
    }

    @Test
    void negativeValues() {
        ListNode head = buildList(-1, 0, 1);
        assertListEquals(new int[]{1, 0, -1}, solution.reverseList(head));
    }

    @Test
    void duplicateValues() {
        ListNode head = buildList(1, 1, 2, 2);
        assertListEquals(new int[]{2, 2, 1, 1}, solution.reverseList(head));
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

    private void assertListEquals(int[] expected, ListNode head) {
        for (int val : expected) {
            assertNotNull(head, "List ended early, expected value: " + val);
            assertEquals(val, head.val);
            head = head.next;
        }
        assertNull(head, "List has extra nodes");
    }
}
