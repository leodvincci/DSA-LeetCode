package com.penrose.leetcode.fastandslowpointers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class L142LinkedListCycleIITest {

    private L142LinkedListCycleII solution;

    @BeforeEach
    void setUp() {
        solution = new L142LinkedListCycleII();
    }

    @Test
    void example1_cycleAtPos1() {
        // [3, 2, 0, -4], pos = 1
        ListNode head = buildList(3, 2, 0, -4);
        ListNode cycleNode = createCycle(head, 1);
        assertEquals(cycleNode, solution.detectCycle(head));
    }

    @Test
    void example2_cycleAtPos0() {
        // [1, 2], pos = 0
        ListNode head = buildList(1, 2);
        ListNode cycleNode = createCycle(head, 0);
        assertEquals(cycleNode, solution.detectCycle(head));
    }

    @Test
    void example3_noCycle() {
        // [1], pos = -1
        ListNode head = buildList(1);
        assertNull(solution.detectCycle(head));
    }

    @Test
    void nullHead() {
        assertNull(solution.detectCycle(null));
    }

    @Test
    void singleNodeNoCycle() {
        ListNode head = buildList(1);
        assertNull(solution.detectCycle(head));
    }

    @Test
    void singleNodeCycle() {
        ListNode head = buildList(1);
        ListNode cycleNode = createCycle(head, 0);
        assertEquals(cycleNode, solution.detectCycle(head));
    }

    @Test
    void longListNoCycle() {
        ListNode head = buildList(1, 2, 3, 4, 5, 6, 7);
        assertNull(solution.detectCycle(head));
    }

    @Test
    void longListCycleAtTail() {
        // Tail connects back to last node
        ListNode head = buildList(1, 2, 3, 4, 5);
        ListNode cycleNode = createCycle(head, 4);
        assertEquals(cycleNode, solution.detectCycle(head));
    }

    @Test
    void longListCycleInMiddle() {
        ListNode head = buildList(1, 2, 3, 4, 5);
        ListNode cycleNode = createCycle(head, 2);
        assertEquals(cycleNode, solution.detectCycle(head));
    }

    @Test
    void longListCycleAtHead() {
        ListNode head = buildList(1, 2, 3, 4, 5);
        ListNode cycleNode = createCycle(head, 0);
        assertEquals(cycleNode, solution.detectCycle(head));
    }

    @Test
    void twoNodesNoCycle() {
        ListNode head = buildList(1, 2);
        assertNull(solution.detectCycle(head));
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

    private ListNode createCycle(ListNode head, int pos) {
        ListNode cycleTarget = head;
        for (int i = 0; i < pos; i++) {
            cycleTarget = cycleTarget.next;
        }
        ListNode tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = cycleTarget;
        return cycleTarget;
    }
}