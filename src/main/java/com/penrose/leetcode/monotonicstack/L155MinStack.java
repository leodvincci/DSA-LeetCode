package com.penrose.leetcode.monotonicstack;

import java.util.Stack;

/**
 * 155. Min Stack
 * <p>
 * Design a stack that supports push, pop, top, and retrieving the minimum
 * element in constant time.
 * <p>
 * Implement the MinStack class:
 * - MinStack() initializes the stack object.
 * - void push(int val) pushes the element val onto the stack.
 * - void pop() removes the element on the top of the stack.
 * - int top() gets the top element of the stack.
 * - int getMin() retrieves the minimum element in the stack.
 * <p>
 * You must implement a solution with O(1) time complexity for each function.
 * <p>
 * Constraints:
 * -2^31 <= val <= 2^31 - 1
 * Methods pop, top and getMin operations will always be called on non-empty stacks.
 * At most 3 * 10^4 calls will be made to push, pop, top, and getMin.
 */
public class L155MinStack {
    Stack<Integer> stack = new Stack<>();
    Stack<Integer> minStack = new Stack<>();

    public L155MinStack() {
        // TODO: implement

    }

    public void push(int val) {
        // TODO: implement
        //I keep forgeting the = values. I cant JUST have less than. I need <=
        stack.push(val);
        if (minStack.isEmpty() || val <= minStack.peek()) minStack.push(val);
    }

    public void pop() {
        // TODO: implement
        //why use equal here vs ==
        if (stack.peek().equals(minStack.peek())){
            minStack.pop();
        }

        stack.pop();

    }

    public int top() {
        // TODO: implement
        return stack.peek();
    }

    public int getMin() {
        // TODO: implement
        return minStack.peek();
    }
}
