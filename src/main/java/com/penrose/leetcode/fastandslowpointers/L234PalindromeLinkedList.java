package com.penrose.leetcode.fastandslowpointers;

public class L234PalindromeLinkedList {

    public boolean isPalindrome(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;

        while(fast != null && fast.next !=null){
            slow = slow.next;


            fast = fast.next.next;

        }

        ListNode prev = null;
        ListNode curr = slow;
        ListNode next = null;

        while (curr!=null){
            next = curr.next;
            curr.next = prev;

            prev = curr;
            curr = next;
        }

        while (prev != null && head !=null){
            if(prev.val != head.val){
                return false;
            }
            prev = prev.next;
            head = head.next;
        }

        return true;
    }
}
