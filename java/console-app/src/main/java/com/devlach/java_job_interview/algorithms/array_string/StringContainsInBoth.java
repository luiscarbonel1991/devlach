package com.devlach.java_job_interview.algorithms.array_string;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class StringContainsInBoth {

    /*
     * Write a function, f(a, b), which takes two String arguments and returns a String containing only the characters found in
     * both Strings, in the order of a. Your implementation should exhibit O(n) complexity.*/


    public static void main(String[] args) {
        System.out.println("fWithSet(\"hello\", \"world\") = " + fWithSet("hello", "world"));
        System.out.println("fWithIndexOf(\"hello\", \"world\") = " + fWithIndexOf("hello", "world"));
    }
    public static String fWithSet(String a, String b) {
        if (a == null && b == null) return "";
        if (a == null || b == null) return "";

        Set<Character> uniqueACharacters = new HashSet<>();
        for (char c : a.toCharArray()) {
            uniqueACharacters.add(c);
        }

        StringBuilder builder = new StringBuilder();
        for (char c : b.toCharArray()) {
            if (uniqueACharacters.contains(c)) {
                builder.append(c);
            }
        }

        return builder.toString();
    }

    public static String fWithIndexOf(String a, String b) {
        if (a == null && b == null) return "";
        if (a == null || b == null) return "";


        LinkedHashSet<Character> unique = new LinkedHashSet<>();
        for (char c: a.toCharArray()) {
            if(b.indexOf(c) != -1) {
                unique.add(c);
            }
        }
        StringBuilder builder = new StringBuilder();
        unique.forEach(builder::append);
        return builder.toString();
    }
}
