package com.penrose.leetcode.fastandslowpointers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class L141LinkedListCycleTest {

    private L141LinkedListCycle solution;

    @BeforeEach
    void setUp() {
        solution = new L141LinkedListCycle();
    }

    @Test
    void example1_cycleAtPos1() {
        // [3, 2, 0, -4], pos = 1
        ListNode head = buildList(3, 2, 0, -4);
        createCycle(head, 1);
        assertTrue(solution.hasCycle(head));
    }

    @Test
    void example2_cycleAtPos0() {
        // [1, 2], pos = 0
        ListNode head = buildList(1, 2);
        createCycle(head, 0);
        assertTrue(solution.hasCycle(head));
    }

    @Test
    void example3_noCycle() {
        // [1], pos = -1
        ListNode head = buildList(1);
        assertFalse(solution.hasCycle(head));
    }

    @Test
    void nullHead() {
        assertFalse(solution.hasCycle(null));
    }

    @Test
    void singleNodeNoCycle() {
        ListNode head = buildList(1);
        assertFalse(solution.hasCycle(head));
    }

    @Test
    void singleNodeCycle() {
        ListNode head = buildList(1);
        createCycle(head, 0);
        assertTrue(solution.hasCycle(head));
    }

    @Test
    void longListNoCycle() {
        ListNode head = buildList(1, 2, 3, 4, 5, 6, 7);
        assertFalse(solution.hasCycle(head));
    }

    @Test
    void longListCycleAtTail() {
        // Tail connects back to last node
        ListNode head = buildList(1, 2, 3, 4, 5);
        createCycle(head, 4);
        assertTrue(solution.hasCycle(head));
    }

    @Test
    void longListCycleInMiddle() {
        ListNode head = buildList(1, 2, 3, 4, 5);
        createCycle(head, 2);
        assertTrue(solution.hasCycle(head));
    }

    @Test
    void twoNodesNoCycle() {
        ListNode head = buildList(1, 2);
        assertFalse(solution.hasCycle(head));
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

    private void createCycle(ListNode head, int pos) {
        ListNode cycleTarget = head;
        for (int i = 0; i < pos; i++) {
            cycleTarget = cycleTarget.next;
        }
        ListNode tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = cycleTarget;
    }
}
