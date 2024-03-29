package com.devlach.java_job_interview.algorithms.linkedlist;

public class MiddleOfTheLinkedList {

    public static ListNode middleNode(ListNode head){
        ListNode slow = head;
        ListNode fast = head;
        while(fast != null && fast.next != null){
            slow = slow.next; // 1
            fast = fast.next.next; // 2
        }
        return slow;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1,
                new ListNode(2,
                        new ListNode(3,
                                new ListNode(4,
                                        new ListNode(5,
                                                new ListNode(6))))));
        System.out.println("middleNode(head) = " + middleNode(head).val);
    }
}
