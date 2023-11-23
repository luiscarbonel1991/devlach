package com.devlach.java_job_interview.algorithms;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LongestSubstringWithoutRepeatingCharacters {

    /*
    Given a string s, find the length of the longest
substring
 without repeating characters.



Example 1:

Input: s = "abcabcbb"
Output: 3
Explanation: The answer is "abc", with the length of 3.
Example 2:

Input: s = "bbbbb"
Output: 1
Explanation: The answer is "b", with the length of 1.
Example 3:

Input: s = "pwwkew"
Output: 3
Explanation: The answer is "wke", with the length of 3.
Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.

     */
    public static void main(String[] args) {

        LongestSubstringWithoutRepeatingCharacters l = new LongestSubstringWithoutRepeatingCharacters();
        System.out.println(l.lengthOfLongestSubstring("abcabcbb"));
        System.out.println(l.lengthOfLongestSubstringUsingMap("abcabcbb"));
        System.out.println(l.lengthOfLongestSubstring("bbbbb"));
        System.out.println(l.lengthOfLongestSubstring("pwwkew"));



    }

    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        int maxLength = 0;
        int[] index = new int[128]; // current index of character
        for (int right = 0, left = 0; right < n; right++) { // right is the right pointer
            left = Math.max(index[s.charAt(right)], left); // left is the left pointer
            maxLength = Math.max(maxLength, right - left + 1); // right - left + 1 is the length of the substring
            index[s.charAt(right)] = right + 1; // update the index of the character
        }
        return maxLength;
    }

    private int lengthOfLongestSubstringUsingMap(String s) {
        int maxLength = 0;
        Map<Character, Integer> visitedCharacters = new HashMap<>();
        for (int rigth = 0, left = 0; rigth < s.length(); rigth++){
            if(visitedCharacters.containsKey(s.charAt(rigth)) &&
            visitedCharacters.get(s.charAt(rigth)) >= left) {
                left = visitedCharacters.get(s.charAt(rigth)) + 1;
            }
            maxLength = Math.max(maxLength, rigth - left + 1);
            visitedCharacters.put(s.charAt(rigth), rigth);
        }
        return maxLength;
    }
}


