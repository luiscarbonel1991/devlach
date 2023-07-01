package com.devlach.java_job_interview.algorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
Given two strings ransomNote and magazine, return true if ransomNote can be constructed by using the letters from magazine and false otherwise.

Each letter in magazine can only be used once in ransomNote.



Example 1:

Input: ransomNote = "a", magazine = "b"
Output: false
Example 2:

Input: ransomNote = "aa", magazine = "ab"
Output: false
Example 3:

Input: ransomNote = "aa", magazine = "aab"
Output: true
 */
public class RansomNote {

    public static void main(String[] args) {
        System.out.println("canConstruct(\"a\", \"b\") = " + canConstruct("a", "b"));
        System.out.println("canConstruct(\"aa\", \"ab\") = " + canConstruct("aa", "ab"));
        System.out.println("canConstruct(\"aa\", \"aab\") = " + canConstruct("aa", "aab"));
        System.out.println("canConstruct(\"bg\", \"aab\") = " + canConstruct("bg", "efjbdfbdgfjhhaiigfhbaejahgfbbgbjagbddfgdiaigdadhcfcj"));
    }
    public static boolean canConstruct(String ransomNote, String magazine) {

        Map<Character, Integer> occurrences = new HashMap<>();
        for (char m: magazine.toCharArray()) {
            occurrences.merge(m, 1, Integer::sum);
        }

        for(char r: ransomNote.toCharArray()){
            int count = occurrences.getOrDefault(r, 0);
            if(count <= 0){
                return false;
            }
            occurrences.merge(r, -1, Integer::sum);
        }
        return true;
    }
}
