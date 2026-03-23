package com.penrose.leetcode.monotonicstack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 20. Valid Parentheses
 * <p>
 * Given a string s containing just the characters '(', ')', '{', '}', '[' and ']',
 * determine if the input string is valid.
 * <p>
 * An input string is valid if:
 * - Open brackets must be closed by the same type of brackets.
 * - Open brackets must be closed in the correct order.
 * - Every close bracket has a corresponding open bracket of the same type.
 * <p>
 * Constraints:
 * 1 <= s.length <= 10^4
 * s consists of parentheses only '()[]{}'.
 */
public class L20ValidParentheses {

    public boolean isValid(String s) {
        // TODO: implement your solution here

        Deque<Character> stack = new ArrayDeque<>();

        for (int i = 0; i < s.length(); i++) {
            char currChar = s.charAt(i);
            if (currChar == '{' || currChar == '[' || currChar == '(') {
                stack.add(currChar);
            } else if (!stack.isEmpty()) {
                if (stack.peekLast() == '(' && currChar != ')') return false;
                if (stack.peekLast() == '[' && currChar != ']') return false;
                if (stack.peekLast() == '{' && currChar != '}') return false;
                stack.removeLast();
            } else {
                return false;
            }
        }
        return stack.isEmpty();
    }
}
