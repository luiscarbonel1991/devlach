package com.devlach.java_job_interview.algorithms.linkedlist;

import java.util.Objects;

public class MergeTwoSortedLists {

    public static void main(String[] args) {
         
        ListNode node = new ListNode(1);
        node.next = new ListNode(2);
        node.next.next = new ListNode(2);

        ListNode node2 = new ListNode(1);
        node2.next = new ListNode(3);
        node2.next.next = new ListNode(4);
        
        var nodeResult =  mergeTwoLists(node, node2);
        System.out.println("nodeResult = " + nodeResult);

        var person1 = new Person();
        var person2 = person1;
        System.out.println("person1 = " + person1);
        System.out.println("person2 = " + person2);

        person2.setAge(20);
        person2.setName("New Name");

        System.out.println("person1 Pos = " + person1);
        System.out.println("person2 Pos = " + person2);

        System.out.println("person1 hashCode = " + person1.hashCode());
        System.out.println("person2 hashCode = " + person2.hashCode());
    }

    static class Person{
        private int age = 0;
        private String name = "Hola person 1";

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "age=" + age +
                    ", name='" + name + '\'' +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Person person = (Person) o;
            return age == person.age && Objects.equals(name, person.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(age, name);
        }
    }

    /**
     * You are given the heads of two sorted linked lists list1 and list2.
     * <p>
     * Merge the two lists into one sorted list. The list should be made by splicing together the nodes of the first two lists.
     * <p>
     * Return the head of the merged linked list.
     *
     * @param list1, list2
     */
    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {

        ListNode dummyHead = new ListNode(-1);
        ListNode current = dummyHead;

        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                current.next = list1;
                list1 = list1.next;
            } else {
                current.next = list2;
                list2 = list2.next;
            }
            current = current.next;
        }

        // Adjuntar cualquier nodo restante
        if (list1 != null) {
            current.next = list1;
        } else if (list2 != null) {
            current.next = list2;
        }

        return dummyHead.next;
    }
}
