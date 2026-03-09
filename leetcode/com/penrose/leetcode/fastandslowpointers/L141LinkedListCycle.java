package com.penrose.leetcode.fastandslowpointers;

import java.util.HashMap;
import java.util.HashSet;

/**
 * 141. Linked List Cycle
 * <p>
 * Given head, the head of a linked list, determine if the linked list has a cycle in it.
 * <p>
 * There is a cycle in a linked list if there is some node in the list that can be reached
 * again by continuously following the next pointer. Internally, pos is used to denote the
 * index of the node that tail's next pointer is connected to. Note that pos is not passed
 * as a parameter.
 * <p>
 * Return true if there is a cycle in the linked list. Otherwise, return false.
 * <p>
 * Constraints:
 * The number of the nodes in the list is in the range [0, 10^4].
 * -10^5 <= Node.val <= 10^5
 * pos is -1 or a valid index in the linked-list.
 */
public class L141LinkedListCycle {

//    public boolean hasCycle(ListNode head) {
//        // TODO: implement your solution here
//        HashSet<ListNode> set = new HashSet<>();
//
//        while (head != null){
//            if(set.contains(head)){
//                return true;
//            }else{
//                set.add(head);
//            }
//
//            head = head.next;
//        }
//        return false;
//    }


    public boolean hasCycle(ListNode head) {
        // TODO: implement your solution here

        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }

        return false;
    }
}
