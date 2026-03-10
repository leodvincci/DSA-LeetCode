package com.penrose.leetcode.fastandslowpointers;

public class L206ReverseLinkedList {

    public ListNode reverseList(ListNode head) {

        ListNode next;
        ListNode prev = null;
        ListNode curr = head;

        while(curr != null){
            next = curr.next;
            curr.next = prev;

            prev = curr;
            curr = next;
        }

        return prev;
    }
}
