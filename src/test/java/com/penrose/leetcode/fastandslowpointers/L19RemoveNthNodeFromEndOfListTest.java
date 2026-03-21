package com.penrose.leetcode.fastandslowpointers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class L19RemoveNthNodeFromEndOfListTest {

    private L19RemoveNthNodeFromEndOfList solution;

    @BeforeEach
    void setUp() {
        solution = new L19RemoveNthNodeFromEndOfList();
    }

    @Test
    void example1_removeSecondFromEnd() {
        // [1,2,3,4,5], n = 2 -> [1,2,3,5]
        ListNode head = buildList(1, 2, 3, 4, 5);
        assertArrayEquals(new int[]{1, 2, 3, 5}, toArray(solution.removeNthFromEnd(head, 2)));
    }

    @Test
    void example2_singleNodeRemoveFirst() {
        // [1], n = 1 -> []
        ListNode head = buildList(1);
        assertNull(solution.removeNthFromEnd(head, 1));
    }

    @Test
    void example3_twoNodesRemoveLast() {
        // [1,2], n = 1 -> [1]
        ListNode head = buildList(1, 2);
        assertArrayEquals(new int[]{1}, toArray(solution.removeNthFromEnd(head, 1)));
    }

    @Test
    void removeHead() {
        // [1,2], n = 2 -> [2]
        ListNode head = buildList(1, 2);
        assertArrayEquals(new int[]{2}, toArray(solution.removeNthFromEnd(head, 2)));
    }

    @Test
    void removeFirstFromEnd() {
        // [1,2,3], n = 1 -> [1,2]
        ListNode head = buildList(1, 2, 3);
        assertArrayEquals(new int[]{1, 2}, toArray(solution.removeNthFromEnd(head, 1)));
    }

    @Test
    void removeMiddle() {
        // [1,2,3], n = 2 -> [1,3]
        ListNode head = buildList(1, 2, 3);
        assertArrayEquals(new int[]{1, 3}, toArray(solution.removeNthFromEnd(head, 2)));
    }

    @Test
    void removeHeadOfThree() {
        // [1,2,3], n = 3 -> [2,3]
        ListNode head = buildList(1, 2, 3);
        assertArrayEquals(new int[]{2, 3}, toArray(solution.removeNthFromEnd(head, 3)));
    }

    @Test
    void longerList() {
        // [1,2,3,4,5,6,7], n = 4 -> [1,2,3,5,6,7]
        ListNode head = buildList(1, 2, 3, 4, 5, 6, 7);
        assertArrayEquals(new int[]{1, 2, 3, 5, 6, 7}, toArray(solution.removeNthFromEnd(head, 4)));
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

    private int[] toArray(ListNode head) {
        // First pass to count
        int count = 0;
        ListNode current = head;
        while (current != null) {
            count++;
            current = current.next;
        }
        // Second pass to fill
        int[] result = new int[count];
        current = head;
        for (int i = 0; i < count; i++) {
            result[i] = current.val;
            current = current.next;
        }
        return result;
    }
}