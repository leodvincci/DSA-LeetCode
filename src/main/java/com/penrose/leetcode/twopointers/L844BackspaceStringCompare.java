package com.penrose.leetcode.twopointers;

/**
 * 844. Backspace String Compare
 * <p>
 * Given two strings s and t, return true if they are equal when both are typed
 * into empty text editors. '#' means a backspace character.
 * <p>
 * Note that after backspacing an empty text, the text will continue empty.
 * <p>
 * Constraints:
 * 1 <= s.length, t.length <= 200
 * s and t only contain lowercase letters and '#' characters.
 */
public class L844BackspaceStringCompare {

    public boolean backspaceCompare(String s, String t) {
        // TODO: implement your solution here

        int skipCredits_s = 0;
        int skipCredits_t = 0;

        int leftPointer_s = s.length() - 1;
        int leftPointer_t = t.length() - 1;

        while(leftPointer_t >=0 || leftPointer_s >=0) {


            while (leftPointer_s >= 0) {
                if (s.charAt(leftPointer_s) == '#') {
                    skipCredits_s++;
                    leftPointer_s--;
                } else if (skipCredits_s > 0) {
                    leftPointer_s--;
                    skipCredits_s--;
                } else {
                    break;
                }
            }

            while (leftPointer_t >= 0) {
                if (t.charAt(leftPointer_t) == '#') {
                    skipCredits_t++;
                    leftPointer_t--;
                } else if (skipCredits_t > 0) {
                    leftPointer_t--;
                    skipCredits_t--;
                } else {
                    break;
                }
            }

            if (leftPointer_t >= 0 && leftPointer_s >= 0) {
                if (s.charAt(leftPointer_s) != t.charAt(leftPointer_t)) {
                    return false;
                }
            }else if (leftPointer_t >= 0 || leftPointer_s >= 0) {
                return false;
            }

            leftPointer_t--;
            leftPointer_s--;

        }
        return true;
    }
}
