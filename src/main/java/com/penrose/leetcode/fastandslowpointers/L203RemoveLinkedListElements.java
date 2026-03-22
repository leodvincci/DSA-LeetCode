package com.penrose.leetcode.fastandslowpointers;

public class L203RemoveLinkedListElements {

    public ListNode removeElements(ListNode head, int val) {
        // TODO: Remove all nodes with Node.val == val and return the new head

        ListNode dummyNode = new ListNode(-42);
        dummyNode.next = head;
        ListNode curr = head;
        ListNode prev = dummyNode;

        while (curr != null) {
            if (curr.val == val) {
                prev.next = curr.next;
            } else {
                prev = curr;
            }
            curr = curr.next;
        }
        return dummyNode.next;
    }
}
