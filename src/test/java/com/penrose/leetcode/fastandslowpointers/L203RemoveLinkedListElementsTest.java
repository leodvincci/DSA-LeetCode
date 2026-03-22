package com.penrose.leetcode.fastandslowpointers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class L203RemoveLinkedListElementsTest {

    private L203RemoveLinkedListElements solution;

    @BeforeEach
    void setUp() {
        solution = new L203RemoveLinkedListElements();
    }

    @Test
    void example1_removeFromMiddleAndEnd() {
        ListNode head = buildList(1, 2, 6, 3, 4, 5, 6);
        ListNode result = solution.removeElements(head, 6);
        assertListEquals(new int[]{1, 2, 3, 4, 5}, result);
    }

    @Test
    void example2_emptyList() {
        ListNode result = solution.removeElements(null, 1);
        assertNull(result);
    }

    @Test
    void example3_removeAllNodes() {
        ListNode head = buildList(7, 7, 7, 7);
        ListNode result = solution.removeElements(head, 7);
        assertNull(result);
    }

    @Test
    void removeFromHead() {
        ListNode head = buildList(1, 1, 2, 3);
        ListNode result = solution.removeElements(head, 1);
        assertListEquals(new int[]{2, 3}, result);
    }

    @Test
    void removeFromTail() {
        ListNode head = buildList(1, 2, 3, 3);
        ListNode result = solution.removeElements(head, 3);
        assertListEquals(new int[]{1, 2}, result);
    }

    @Test
    void singleElement_removed() {
        ListNode head = buildList(5);
        ListNode result = solution.removeElements(head, 5);
        assertNull(result);
    }

    @Test
    void singleElement_kept() {
        ListNode head = buildList(5);
        ListNode result = solution.removeElements(head, 3);
        assertListEquals(new int[]{5}, result);
    }

    @Test
    void noMatchingElements() {
        ListNode head = buildList(1, 2, 3, 4);
        ListNode result = solution.removeElements(head, 9);
        assertListEquals(new int[]{1, 2, 3, 4}, result);
    }

    @Test
    void consecutiveRemovals() {
        ListNode head = buildList(1, 2, 2, 2, 3);
        ListNode result = solution.removeElements(head, 2);
        assertListEquals(new int[]{1, 3}, result);
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
            assertNotNull(head, "List ended early, expected " + val);
            assertEquals(val, head.val);
            head = head.next;
        }
        assertNull(head, "List has extra nodes");
    }
}
