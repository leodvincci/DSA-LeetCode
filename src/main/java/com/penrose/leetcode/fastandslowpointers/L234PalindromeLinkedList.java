package com.penrose.leetcode.fastandslowpointers;

public class L234PalindromeLinkedList {

    public boolean isPalindrome(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;

        while(fast != null && fast.next !=null){
            slow = slow.next;


            fast = fast.next.next;

        }

        System.out.println("MIDDLE --> " + slow.val);

        return false;
    }
}
