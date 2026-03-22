package com.penrose.leetcode.monotonicstack;

import java.util.Arrays;
import java.util.Stack;

/**
 * 739. Daily Temperatures
 * <p>
 * Given an array of integers temperatures represents the daily temperatures,
 * return an array answer such that answer[i] is the number of days you have
 * to wait after the ith day to get a warmer temperature. If there is no future
 * day for which this is possible, keep answer[i] == 0 instead.
 * <p>
 * Constraints:
 * 1 <= temperatures.length <= 10^5
 * 30 <= temperatures[i] <= 100
 */
public class L739DailyTemperatures {

    public int[] dailyTemperatures(int[] temperatures) {
        Stack<Integer> stack = new Stack<>();
        int[] res = new int[temperatures.length];

        for (int i = 0; i < temperatures.length; i++) {
            while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                int diff = i - stack.peek();
                res[stack.pop()] = diff;
            }
            stack.push(i);
        }
        return res;
    }
}
