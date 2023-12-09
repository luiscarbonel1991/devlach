package com.devlach.java_job_interview.algorithms.linkedlist;

/** Simple Linked List definition
 *
 */
class ListNode{
    int val;
    ListNode next;

    ListNode() {}

    public ListNode(int val) {
        this.val = val;
        this.next = null;
    }

    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
