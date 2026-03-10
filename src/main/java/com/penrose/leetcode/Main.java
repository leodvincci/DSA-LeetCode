package com.penrose.leetcode;


public class Main {
    public static void main(String[] args) {
        ListNode head = new ListNode(7);
        ListNode a = new ListNode(5);
        ListNode b = new ListNode(8);
        head.next = a;
        a.next = b;


        ListNode prev = null;
        ListNode curr = head;
        ListNode next = null;

        while(curr != null){
            //secure the next val
            next = curr.next;

            //the new next val is the prev val
            curr.next = prev;

            //prev val will be the current
            prev = curr;

            //the current is now the next val.
            curr = next;
        }




        while (prev != null) {
            System.out.print(prev.val);
            prev = prev.next;
        }

    }
}
