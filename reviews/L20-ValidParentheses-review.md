# L20 Valid Parentheses — Code Review

## Solution Reviewed

```java
package com.penrose.leetcode.monotonicstack;

import java.util.ArrayDeque;
import java.util.Deque;

public class L20ValidParentheses {

    public boolean isValid(String s) {
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
```

## 1. Overall Impression

This is a clean, correct solution that shows solid understanding of the stack pattern for bracket matching. You correctly identified the three cases (open bracket → push, close bracket with match → pop, close bracket without match → fail), and your final `stack.isEmpty()` check handles the "unclosed brackets" edge case. The logic is easy to follow and the code is interview-ready. The only things worth discussing are stylistic — using `push`/`pop`/`peek` instead of `add`/`removeLast`/`peekLast`, and the leftover TODO comment. This would pass in an interview.

## 2. What I Did Well

- **Correct approach on the first pass.** Push opening brackets, validate and pop on closing brackets, return whether the stack is empty. That's the complete algorithm, and you got it right.
- **Used `ArrayDeque` instead of `Stack`.** You took the feedback from the L739 review and applied it immediately. This signals Java fluency.
- **Early return on empty stack (line 34–36).** If you encounter a closing bracket with nothing on the stack, you return `false` immediately instead of letting it fall through. Clean and correct.
- **Final `return stack.isEmpty()` (line 38).** This catches the case where there are unmatched opening brackets remaining (e.g., `"((("`, `"({}"`) — a common edge case that trips people up.
- **Good variable naming.** `currChar` is clear and descriptive. `stack` is self-documenting.
- **Clean structure.** The if/else-if/else chain covers all three cases with no redundancy.

## 3. What Needs Improvement

- **Deque method choice: `add`/`peekLast`/`removeLast` vs `push`/`peek`/`pop`.** On `ArrayDeque`, `push`/`pop`/`peek` operate on the *front* of the deque (stack semantics), while `add`/`peekLast`/`removeLast` operate on the *back*. Your code works because you're consistently using the "last" end — but you're effectively using the deque as a queue-end stack. The idiomatic way to use `ArrayDeque` as a stack is `push`/`pop`/`peek`, which all operate on the same end and make the intent immediately clear. An interviewer seeing `peekLast` might pause and ask "why not `peek`?"

- **Leftover TODO comment.** Small thing, but `// TODO: implement your solution here` should be removed once you've implemented the solution. In an interview, it suggests you forgot to clean up.

- **The matching logic could be more concise with a Map.** The three separate if-statements work, but a `Map<Character, Character>` mapping openers to closers (or vice versa) is a common interview pattern that scales better and reads more declaratively. That said, with only three bracket types, the explicit checks are perfectly acceptable — this is a "nice to know" not a "must fix."

## 4. Correctness Review

**Fully correct.**

Let's trace through a tricky case — `"([)]"` (Example 5, should return `false`):

```
i=0, '(': opening → push.         stack = ['(']
i=1, '[': opening → push.         stack = ['(', '[']
i=2, ')': closing, stack not empty.
         peekLast is '[', and '[' != '(' so skip line 30.
         peekLast is '[', and currChar is ')' not ']' → return false ✓
```

And `"({[]})"` (deeply nested, should return `true`):

```
i=0, '(': push.                   stack = ['(']
i=1, '{': push.                   stack = ['(', '{']
i=2, '[': push.                   stack = ['(', '{', '[']
i=3, ']': peekLast='[', currChar=']' → match. removeLast.  stack = ['(', '{']
i=4, '}': peekLast='{', currChar='}' → match. removeLast.  stack = ['(']
i=5, ')': peekLast='(', currChar=')' → match. removeLast.  stack = []

return stack.isEmpty() → true ✓
```

All edge cases are handled: empty stack on close, unmatched opens at end, mismatched types, interleaved brackets.

## 5. Java-Specific Review

- **Compiles: Yes.** Clean compilation, all 17 tests pass.
- **`Deque<Character>` with `ArrayDeque`: Good.** Correct modern Java choice over legacy `Stack`.
- **Method consistency issue:** You use `add` to push, `peekLast` to peek, and `removeLast` to pop. These all operate on the tail of the deque, so it works. But the conventional stack API on `ArrayDeque` is `push`/`peek`/`pop` (which operate on the head). Using the stack-named methods makes intent clearer:
  ```java
  stack.push(currChar);      // instead of stack.add(currChar)
  stack.peek()               // instead of stack.peekLast()
  stack.pop()                // instead of stack.removeLast()
  ```
- **`s.charAt(i)` in a for loop is fine.** Some prefer `for (char c : s.toCharArray())` for readability, but your approach avoids allocating a new array. Either is acceptable in an interview.
- **No unnecessary object creation.** Clean.

## 6. Time and Space Complexity Review

- **Time Complexity: O(n)** — Single pass through the string, with O(1) operations per character (push, peek, pop, comparisons). Correct and optimal.
- **Space Complexity: O(n)** — In the worst case (all opening brackets like `"(((([[["`), the stack holds all n characters. Correct.

This is **optimal.** You must examine every character at least once, and the stack is necessary to track nesting order. There's no way to do this in O(1) space while maintaining correctness for nested/interleaved cases.

## 7. Pattern / Concept Assessment

**Pattern:** Stack (bracket matching / parentheses validation).

**Assessment: Strong.**

This is one of the most fundamental stack problems, and you executed it cleanly. The three-way branching (open → push, close with match → pop, close without match → fail) is the canonical approach, and you implemented it without unnecessary special cases. Compared to your first L739 attempt where you over-complicated the stack logic with extra branches, here you kept it simple and direct. That's clear growth in how you think about stack problems.

## 8. Interview Readiness Feedback

- **Understanding depth:** Strong. The solution shows you understand *why* a stack works here — LIFO ordering naturally validates that brackets close in the correct order.
- **Pattern matching vs reasoning:** This reads like genuine reasoning, not rote memorization. The structure is natural and the edge cases are handled through logic, not patchwork.
- **Confidence in Java:** Good. Using `ArrayDeque` is a plus. The `peekLast`/`removeLast` usage is slightly non-idiomatic but demonstrates you understand the deque API — just switch to `push`/`pop`/`peek` for cleaner signaling.
- **Interviewer trust:** High. This is straightforward, correct, and easy to follow. An interviewer would have no trouble verifying it.
- **Coding style impression:** "Clean, deliberate, knows their data structures." That's what you want.

## 9. Best Next Improvement

**Use the idiomatic stack methods (`push`/`peek`/`pop`) on `ArrayDeque`.** This is the one thing that would make the code read more naturally as a stack solution. It's a small change, but it aligns your API usage with your intent — and in interviews, clarity of intent matters.

## 10. Polished Version

Your solution is already strong. Here's a polished version with the minor refinements:

```java
package com.penrose.leetcode.monotonicstack;

import java.util.ArrayDeque;
import java.util.Deque;

public class L20ValidParentheses {

    public boolean isValid(String s) {
        Deque<Character> stack = new ArrayDeque<>();

        for (int i = 0; i < s.length(); i++) {
            char curr = s.charAt(i);
            if (curr == '(' || curr == '[' || curr == '{') {
                stack.push(curr);
            } else if (!stack.isEmpty()) {
                if (stack.peek() == '(' && curr != ')') return false;
                if (stack.peek() == '[' && curr != ']') return false;
                if (stack.peek() == '{' && curr != '}') return false;
                stack.pop();
            } else {
                return false;
            }
        }
        return stack.isEmpty();
    }
}
```

**What changed:**
- **`add` → `push`, `peekLast` → `peek`, `removeLast` → `pop`** — idiomatic stack API on `ArrayDeque`.
- **Removed the TODO comment.**
- **`currChar` → `curr`** — slightly more concise; either name is fine.
- Core logic is unchanged — it was already correct.

## 11. Coaching Note

This is a really solid showing. You took a classic stack problem, identified the right approach, implemented it cleanly, and handled all the edge cases on your first pass. What stands out most is the progression from L739 to here — in the temperatures problem, you were over-engineering the stack logic with extra branches and special cases. Here, you trusted the pattern and kept it simple. That's exactly the kind of growth that compounds. You're building reliable instincts for when and how to use a stack, and that foundation will carry you through harder problems in this category.

## 12. Drill Recommendation

1. **L155 Min Stack** — design a stack that supports `getMin()` in O(1). Tests whether you can layer additional structure onto the basic stack pattern.
2. **L150 Evaluate Reverse Polish Notation** — stack for expression evaluation. Different *use* of the same data structure, which builds flexibility.
3. **L84 Largest Rectangle in Histogram** (Hard) — when you're ready to push yourself. Combines monotonic stack with the bracket-matching intuition of "what's still open."
