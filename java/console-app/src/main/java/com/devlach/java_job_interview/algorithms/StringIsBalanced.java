package com.devlach.java_job_interview.algorithms;

import java.util.*;

public class StringIsBalanced {

    public static void main(String[] args) {
        System.out.println("isBalance(\"([{}[]])\") = " + isBalance("([{}[]])"));
        System.out.println("isBalance(\"([{}[]])\") = " + isBalanceWithMap("([{}[]])"));
        System.out.println("isBalance(\"{[()()]}\") = " + isBalanceWithMap("{[()()]}"));
    }

    public static boolean isBalance(String s) {
        Deque<Character> stack = new ArrayDeque<>();

        for (char c : s.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else if (c == ')' || c == '}' || c == ']') {
                if (stack.isEmpty()) return false;
                char pop = stack.pop();

                if ((c == ')' && pop != '(') ||
                        (c == '}' && pop != '{') ||
                        (c == ']' && pop != '[')
                ) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    public static boolean isBalanceWithMap(String s) {
        Map<Character, Character> bracketsMap = Map.of(
                ')', '(',
                '}', '{',
                ']', '['
        );
        Deque<Character> stack = new ArrayDeque<>();

        for (char c : s.toCharArray()) {
            if (bracketsMap.containsValue(c)) { // If is an open character
                stack.push(c);
            } else if (bracketsMap.containsKey(c) && (stack.isEmpty() || !Objects.equals(stack.pop(), bracketsMap.get(c)))) {
                    return false;

            }
        }
        return stack.isEmpty();
    }
}
