# L739 Daily Temperatures — Code Review

## 1. Overall Impression

You clearly identified that this is a monotonic stack problem and built the right high-level approach: iterate through temperatures, use a stack of indices, and pop when you find a warmer day. That's the correct instinct and it shows real pattern recognition. The solution produces correct output for the given examples, but there's a subtle structural bug — `i` gets pushed onto the stack twice when the stack empties inside the while loop — and the control flow is more tangled than it needs to be. Debug print statements aside (we all do it), the logic would likely draw follow-up questions from an interviewer, and the duplicate-push bug would probably get caught under scrutiny. With a small restructure, this becomes a clean, interview-ready solution.

## 2. What I Did Well

- **Correct pattern identification.** You recognized this as a monotonic decreasing stack problem and reached for the right tool.
- **Stack of indices, not values.** Storing indices rather than temperatures is exactly right — you need the index to compute the day difference. This shows understanding of *why* the stack is used, not just *that* it's used.
- **Core pop logic is correct.** Lines 31–32 (`i - stack.peek()` then `res[stack.pop()] = diff`) are textbook and clean.
- **The result array is initialized to zeros by default** — you correctly relied on Java's default int array initialization to handle the "no warmer day" case without extra work.

## 3. What Needs Improvement

- **Duplicate push bug (lines 34–37 and line 40).** When the stack empties inside the while loop, you push `i` at line 36, then the while loop exits (since `temperatures[i] > temperatures[i]` is false), and line 40 pushes `i` *again*. Every index that triggers a full stack drain ends up on the stack twice. The output happens to be correct because popping the same index twice overwrites `res[i]` with the same value — but it's still a bug that wastes space and would raise a red flag in an interview.
- **Overly complex branching.** The top-level `if (stack.isEmpty())` (line 25) and the inner `if (stack.isEmpty())` (line 34) are both unnecessary. The standard monotonic stack pattern is much simpler: always do the while-pop first, then always push. No special-casing needed.
- **Debug print statements** (lines 30, 33, 37, 41, 46). Totally normal during development, but these need to be removed before review. In an interview setting, leaving them signals you're not done cleaning up.
- **`Stack` class is legacy.** Java's `Stack` extends `Vector` and is synchronized — `ArrayDeque` is the idiomatic, faster choice.

## 4. Correctness Review

**Partially correct.**

The solution produces correct *results* for all inputs, but contains a structural bug. Here's a focused trace showing the duplicate push:

```
Input: [30, 60, 90]

i=0: stack empty → push 0.          stack = [0]
i=1: 60 > 30 → pop 0, res[0]=1.    stack = []
     stack empty → push 1.          stack = [1]     ← line 36
     while exits (60 > 60 is false)
     push 1 again.                   stack = [1, 1]  ← line 40 (BUG)
i=2: 90 > 60 → pop 1, res[1]=1.    stack = [1]
     90 > 60 → pop 1, res[1]=1.    stack = []       ← redundant work
     push 2.                         stack = [2]
     push 2 again.                   stack = [2, 2]  ← BUG

Result: [1, 1, 0] ← correct, but by coincidence
```

The duplicate entries don't corrupt the output because both copies of the same index yield the same `res[i]` value when popped. But it doubles the stack size in the worst case (O(2n) instead of O(n)) and the logic is fragile — an interviewer tracing through would catch it.

## 5. Java-Specific Review

- **Compiles: Yes.** The code compiles and runs.
- **`Stack` → `ArrayDeque`:** `java.util.Stack` is a legacy class (synchronized, extends `Vector`). Idiomatic Java uses `Deque<Integer> stack = new ArrayDeque<>()` with `push`/`pop`/`peek`. This is a common interview talking point — using `ArrayDeque` signals Java fluency.
- **`stack.add(i)` vs `stack.push(i)`:** You mix `add` (line 26, 36) and `push` (line 40). Both work on `Stack`, but mixing them is inconsistent. Stick with `push`/`pop`/`peek` for stack semantics.
- **Unused import after cleanup:** `java.util.Arrays` is only used for the debug print. Once prints are removed, this import becomes dead code.
- **Variable naming:** `res` is fine for interview code. `stack` is clear. `diff` is used once and could be inlined, but it's not a problem.

## 6. Time and Space Complexity Review

- **Time Complexity: O(n)** — Each index is pushed and popped at most once (ignoring the duplicate bug). Correct.
- **Space Complexity: O(n)** — The stack can hold up to n indices in the worst case (strictly decreasing input). Correct.

This is **optimal**. There's no way to solve this in better than O(n) time since you must examine every element. Your complexity understanding is solid.

## 7. Pattern / Concept Assessment

**Pattern:** Monotonic Stack (specifically, a monotonic *decreasing* stack).

**Assessment: Developing.**

You clearly know *what* a monotonic stack is and *when* to use it. The fact that you reached for it immediately is a good sign. But the tangled control flow — special-casing the empty stack at two levels, the inner `isEmpty()` check with the early push — suggests you're still building intuition for the *canonical form* of the pattern. The standard monotonic stack loop is:

```java
for each element:
    while stack is not empty AND current > stack top:
        pop and process
    push current
```

That's it. No `if/else` at the top level. No inner empty check. Once this two-line structure clicks, you'll write these problems much faster and with fewer bugs.

## 8. Interview Readiness Feedback

- **Pattern recognition:** Strong. You'd correctly identify the approach in an interview, which is half the battle.
- **Implementation confidence:** Needs work. The branching complexity and duplicate-push bug would likely lead to a back-and-forth with the interviewer. They might ask "what happens when the stack empties inside the while loop?" — and the current answer would reveal the issue.
- **Debug prints:** Would need to be removed. In a live interview, writing them is fine for debugging, but clean them up before saying "I'm done."
- **Overall impression you'd give:** "Understands the concept, got close, but implementation needs tightening." That's a soft pass to soft fail depending on the company. With the cleanup below, it becomes a clear pass.

## 9. Best Next Improvement

**Internalize the canonical monotonic stack template.** Your bug and complexity both stem from trying to handle the "empty stack" case specially instead of trusting the pattern. Practice writing the while-then-push structure until it's muscle memory — it eliminates an entire class of bugs.

## 10. Rewrite Suggestion

```java
package com.penrose.leetcode.monotonicstack;

import java.util.ArrayDeque;
import java.util.Deque;

public class L739DailyTemperatures {

    public int[] dailyTemperatures(int[] temperatures) {
        Deque<Integer> stack = new ArrayDeque<>();
        int[] res = new int[temperatures.length];

        for (int i = 0; i < temperatures.length; i++) {
            while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                int prev = stack.pop();
                res[prev] = i - prev;
            }
            stack.push(i);
        }

        return res;
    }
}
```

**What changed:**
- **Removed all branching.** The while-then-push structure handles every case: empty stack, non-empty stack, single pop, multiple pops. No special cases needed.
- **`ArrayDeque` instead of `Stack`.** Idiomatic, faster, no synchronization overhead.
- **Removed debug prints and unused imports.**
- **`int prev = stack.pop()`** makes the pop-and-assign more readable than the inline `res[stack.pop()] = diff` approach.
- **No duplicate push.** Each index is pushed exactly once (line after the while loop), regardless of what happened during popping.

## 11. Coaching Note

You're doing the right thing — you identified the pattern, reached for the right data structure, and got a working (if slightly buggy) solution. That's genuinely solid ground to be standing on. The gap between where you are and a clean interview solution is not a conceptual gap — it's a *structural familiarity* gap. You understand *why* the monotonic stack works; you just need more reps with the clean form so the implementation flows without the extra scaffolding. A few more stack problems using the canonical template and this will feel second nature.

## 12. Drill Recommendation

1. **Rewrite this problem from scratch** using only the while-then-push template. No peeking. Time yourself.
2. **L496 Next Greater Element I** — same monotonic stack pattern with a HashMap twist. Great for reinforcing the template.
3. **L901 Online Stock Span** — monotonic stack in a slightly different context. Builds flexibility with the pattern.

---

# L739 Daily Temperatures — Second Review (Post-Revision)

## Updated Solution

```java
package com.penrose.leetcode.monotonicstack;

import java.util.Arrays;
import java.util.Stack;

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
```

## 1. Overall Impression

This is a massive improvement. You went from a tangled, over-branched implementation with a duplicate-push bug to the clean, canonical monotonic stack pattern in one revision. The core logic — while-then-push — is now exactly right. The solution is correct, optimal, and reads clearly. All 11 test cases pass (including edge cases like all-same, strictly decreasing, valley shapes, and single elements). There are only two minor polish items remaining (`Stack` vs `ArrayDeque` and an unused import), neither of which affects correctness. This would pass in an interview.

## 2. What I Did Well

- **Adopted the canonical template.** The while-then-push structure (lines 25–29) is textbook. You eliminated all the unnecessary branching — the top-level `if (stack.isEmpty())`, the inner `if (stack.isEmpty())`, the early push inside the while loop — all gone. This is exactly the restructure that was needed.
- **Fixed the duplicate-push bug.** `stack.push(i)` now happens exactly once per iteration (line 29), unconditionally after the while loop. No index is ever pushed twice. This was the main correctness issue from round one, and you nailed it.
- **Removed all debug print statements.** The `System.out.println` calls are gone. The code is clean and interview-ready.
- **Preserved what was already working.** You kept the strengths from round one — stack of indices (not values), relying on Java's default zero-initialization for the result array, and the correct `i - stack.peek()` / `res[stack.pop()]` pop logic.
- **All 11 tests pass.** Including edge cases: single element, all same temperature, strictly decreasing (all zeros), strictly increasing (all ones), warmer day far away, and valley shape. The solution handles all of them correctly.

## 3. What Needs Improvement

The remaining items are minor polish — nothing structural or correctness-related:

- **`Stack` is still a legacy class (line 21).** `java.util.Stack` extends `Vector`, which means every operation is synchronized — unnecessary overhead for single-threaded code. The idiomatic Java choice is:
  ```java
  Deque<Integer> stack = new ArrayDeque<>();
  ```
  This is a well-known interview talking point. Using `ArrayDeque` signals Java fluency and awareness of the collections framework history. An interviewer who notices `Stack` might ask "why not `ArrayDeque`?" — it's a free opportunity to demonstrate depth, or a minor ding if you can't explain the difference.

- **Unused import: `java.util.Arrays` (line 3).** This was used for the debug `Arrays.toString(res)` call that you've since removed. Dead imports are a small thing, but in an interview setting, clean imports signal attention to detail. Remove it.

- **`diff` variable (line 26) could be inlined.** Currently:
  ```java
  int diff = i - stack.peek();
  res[stack.pop()] = diff;
  ```
  Could be simplified to:
  ```java
  int prev = stack.pop();
  res[prev] = i - prev;
  ```
  This is a stylistic preference, not a bug. Your current version is fine — but the `prev` approach is arguably cleaner because it gives a meaningful name to the popped index rather than splitting the computation across `peek()` then `pop()`. Either is acceptable in an interview.

## 4. Correctness Review

**Fully correct.**

Let's trace through Example 1 to confirm the logic is airtight:

```
Input: [73, 74, 75, 71, 69, 72, 76, 73]

i=0 (73):  stack empty, skip while.  Push 0.          stack = [0]
i=1 (74):  74 > 73 → pop 0, res[0] = 1-0 = 1.        stack = []
           stack empty, exit while.  Push 1.           stack = [1]
i=2 (75):  75 > 74 → pop 1, res[1] = 2-1 = 1.        stack = []
           stack empty, exit while.  Push 2.           stack = [2]
i=3 (71):  71 > 75? No. Push 3.                       stack = [2, 3]
i=4 (69):  69 > 71? No. Push 4.                       stack = [2, 3, 4]
i=5 (72):  72 > 69 → pop 4, res[4] = 5-4 = 1.        stack = [2, 3]
           72 > 71 → pop 3, res[3] = 5-3 = 2.        stack = [2]
           72 > 75? No, exit while. Push 5.            stack = [2, 5]
i=6 (76):  76 > 72 → pop 5, res[5] = 6-5 = 1.        stack = [2]
           76 > 75 → pop 2, res[2] = 6-2 = 4.        stack = []
           stack empty, exit while. Push 6.            stack = [6]
i=7 (73):  73 > 76? No. Push 7.                       stack = [6, 7]

Result: [1, 1, 4, 2, 1, 1, 0, 0] ✓
```

Every index is pushed exactly once and popped at most once. Indices remaining on the stack at the end (6 and 7) correctly keep their default `res` value of 0, meaning no warmer day exists. The logic is clean, correct, and leaves no ambiguity.

## 5. Java-Specific Review

- **Compiles: Yes.** Clean compilation, all tests pass.
- **`Stack` → `ArrayDeque` (only remaining Java issue).** Swap `Stack<Integer>` for `Deque<Integer> stack = new ArrayDeque<>()`. The API is identical (`push`, `pop`, `peek`), so it's a one-line change. `Stack` works, but `ArrayDeque` is what a Java-fluent candidate would reach for.
- **Consistent method usage.** You now use `push` consistently (no more mixing `add` and `push`). Good fix.
- **Dead import.** `java.util.Arrays` on line 3 is unused. Remove it.
- **Method signature is clean.** `int[] dailyTemperatures(int[] temperatures)` — clear parameter name, correct return type, no unnecessary wrappers.
- **No unnecessary object creation.** The only heap allocation beyond the result array is the stack itself, which is necessary. No wasteful boxing beyond what the `Stack<Integer>` requires (another reason to prefer `ArrayDeque` — though both box `int` to `Integer`).

## 6. Time and Space Complexity Review

- **Time Complexity: O(n)** — Each of the n indices is pushed onto the stack exactly once and popped at most once. The while loop across all iterations of the for loop performs at most n pops total. So the total work is O(n) pushes + O(n) pops = **O(n)**.
- **Space Complexity: O(n)** — The stack can hold up to n indices in the worst case (strictly decreasing temperatures like `[100, 99, 98, ..., 30]`). The result array is O(n) but is required output, so depending on convention it may or may not be counted. The auxiliary space is O(n) for the stack.

This is **optimal.** You must examine every element at least once (Ω(n) lower bound), and no approach avoids the stack or equivalent structure. Your solution matches the theoretical best.

## 7. Pattern / Concept Assessment

**Pattern:** Monotonic Stack (monotonic *decreasing* stack).

**Assessment: Strong.**

This is a clear upgrade from "developing" in round one. Here's why:

- You're now using the canonical form of the pattern — `while (!stack.isEmpty() && condition)` followed by an unconditional `push`. This is the template that generalizes across all monotonic stack problems (next greater element, stock span, largest rectangle in histogram, etc.).
- You eliminated every unnecessary special case. The empty-stack check is handled naturally by the `!stack.isEmpty()` guard in the while condition — no separate branch needed.
- The fact that you self-corrected to this structure after one round of feedback shows you internalized the *why*, not just the *what*. That's the difference between pattern matching and true understanding.

## 8. Interview Readiness Feedback

- **Pattern recognition:** Strong. Immediate identification of monotonic stack — this would impress in an interview.
- **Implementation confidence:** Strong. The canonical while-then-push loop is clean, correct, and easy to trace. An interviewer walking through this with you would find no surprises.
- **Code cleanliness:** Near interview-ready. The only things an interviewer might comment on are the `Stack` vs `ArrayDeque` choice and the dead import — both easy to address if asked.
- **Ability to self-correct:** This is a standout quality. Going from a buggy-but-functional first attempt to the clean canonical form in one iteration shows the kind of responsiveness interviewers want to see. In a live interview, this would play as: "candidate had a reasonable first pass, identified the issue when prompted, and cleanly restructured."
- **Overall impression you'd give:** "Solid understanding of the pattern, clean implementation, comfortable in Java." That's a **pass** at most companies.

## 9. Best Next Improvement

**Replace `Stack` with `ArrayDeque` and remove the dead import.** These are the only two things standing between your current solution and a fully polished, no-notes-needed interview answer. It's a 30-second fix, but it signals Java maturity — and if an interviewer asks "why `ArrayDeque`?", you get to explain `Vector` synchronization overhead, which is a strong look.

## 10. Polished Version

Your solution is already strong. Here's the final polished form with the two minor fixes applied:

```java
package com.penrose.leetcode.monotonicstack;

import java.util.ArrayDeque;
import java.util.Deque;

public class L739DailyTemperatures {

    public int[] dailyTemperatures(int[] temperatures) {
        Deque<Integer> stack = new ArrayDeque<>();
        int[] res = new int[temperatures.length];

        for (int i = 0; i < temperatures.length; i++) {
            while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                int prev = stack.pop();
                res[prev] = i - prev;
            }
            stack.push(i);
        }
        return res;
    }
}
```

**What changed from your revision:**
- **`Stack<Integer>` → `Deque<Integer> stack = new ArrayDeque<>()`** — idiomatic, unsynchronized, faster. Same API (`push`/`pop`/`peek`).
- **Removed `import java.util.Arrays`** — dead import from the debug prints you already removed.
- **`int prev = stack.pop()` instead of split `peek()`/`pop()`** — minor readability improvement. Gives the popped index a meaningful name.

Everything else stays the same. Your core logic was already correct.

## 11. Coaching Note

This is what growth looks like. In one revision, you went from a solution that worked-but-had-a-bug to a clean, canonical, interview-ready implementation. And you didn't just copy a template — you restructured your own logic to match the pattern. That matters, because it means you understand *why* the while-then-push structure works, not just *that* it works. The monotonic stack is one of those patterns that shows up in a surprising number of problems (next greater element, stock span, histogram areas, trapping rain water), and you now have the clean mental model to apply it confidently. Keep building on this — you're in a strong spot.

## 12. Drill Recommendation

1. **L496 Next Greater Element I** — same monotonic stack template, but now with a HashMap to map between two arrays. Tests whether you can layer a second data structure on top of the pattern you just solidified.
2. **L901 Online Stock Span** — monotonic stack in an online/streaming context (method called repeatedly instead of array iteration). Tests whether your understanding of the pattern is flexible or rigid.
3. **L84 Largest Rectangle in Histogram** (Hard) — the canonical "hard" monotonic stack problem. When you're ready to stretch, this is the one that separates "knows the pattern" from "owns the pattern."
