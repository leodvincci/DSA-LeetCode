package com.penrose.leetcode.slidingwindow;

import java.util.HashMap;

/**
 * 395. Longest Substring with At Least K Repeating Characters
 * <p>
 * Given a string s and an integer k, return the length of the longest substring
 * of s such that the frequency of each character in this substring is greater
 * than or equal to k.
 * <p>
 * If no such substring exists, return 0.
 * <p>
 * Constraints:
 * 1 <= s.length <= 10^4
 * s consists of only lowercase English letters.
 * 1 <= k <= 10^5
 */
public class L395LongestSubstringKRepeating {

    public int longestSubstring(String s, int k) {
        // TODO: implement your solution here

        return helper(s.toCharArray(), 0, s.length(), k);
    }

    public int helper(char[] chars, int start, int end, int k) {

        HashMap<Character, Integer> map = new HashMap<>();


        if (end - start < k) {
            return 0;
        }

        for (int i = start; i < end; i++) {
            map.put(chars[i], map.getOrDefault(chars[i], 0) + 1);
            System.out.println(chars[i]);
        }
        System.out.println(map);

        for (int i = start; i < end; i++) {
            if (map.get(chars[i]) < k) {
                int j = i + 1;
                while (j < end && map.get(chars[j]) < k) {
                    j++;
                }
                int left = helper(chars, start, i, k);
                int right = helper(chars, j, end, k);
                return Math.max(left, right);
            }

        }

        return end - start;
    }


    public HashMap<Character, Integer> mapper(String s, int k) {
        HashMap<Character, Integer> map = new HashMap<>();
        char[] chars = s.toCharArray();
        for (int i = 0; i < s.length(); i++) {
            map.put(chars[i], map.getOrDefault(chars[i], 0) + 1);
        }
        System.out.println("k= " + k);
        System.out.println(map);

        for (char aChar : chars) {
            if (map.get(aChar) < k) {
                System.out.println(aChar + " is Bad.");
            }
        }
        return map;
    }

}
