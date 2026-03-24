# L26 Remove Duplicates from Sorted Array — Code Review

## Solution Reviewed

```java
package com.penrose.leetcode.twopointers;

/**
 * 26. Remove Duplicates from Sorted Array
 * Pattern: Two Pointers
 * <p>
 * Given an integer array nums sorted in non-decreasing order, remove the
 * duplicates in-place such that each unique element appears only once.
 * Return the number of unique elements.
 * <p>
 * Constraints:
 * 1 <= nums.length <= 3 * 10^4
 * -100 <= nums[i] <= 100
 * nums is sorted in non-decreasing order.
 */
public class L26RemoveDuplicatesFromSortedArray {

    public int removeDuplicates(int[] nums) {
        int reader = 1;
        int writer = 1;

        while(reader < nums.length){
            if (nums[writer - 1] != nums[reader]) {
                nums[writer] = nums[reader];
                writer++;
            }
            reader++;
        }

        return  writer;
    }
}
```

## 1. Overall Impression

This is a clean, correct solution that demonstrates a solid grasp of the reader/writer two-pointer pattern. You identified that the sorted order means duplicates are always adjacent, and you used that insight to build a simple single-pass algorithm with O(1) extra space. The naming is intentional and descriptive, the structure is easy to follow, and the logic has no unnecessary branches. The one subtlety worth discussing is the comparison target — you compare against `nums[writer - 1]` instead of the more common `nums[reader - 1]`, which works but is slightly less intuitive for a reader scanning the code quickly. This would comfortably pass in a real interview.

## 2. What You Did Well

- **Correct pattern identification.** You recognized this as a reader/writer two-pointer problem and implemented it directly. No wasted exploration, no over-engineering.
- **Excellent variable naming.** `reader` and `writer` communicate the roles of each pointer far more clearly than the generic `i`/`j` or `slow`/`fast` you'll see in most solutions. This is a genuine interview advantage — it shows you understand *what* each pointer does, not just *where* it goes.
- **Starting both pointers at 1.** Smart. The first element is always part of the unique set, so there's nothing to check at index 0. Starting at 1 avoids a special case and keeps the loop body clean.
- **Single responsibility in the loop body.** The reader always advances. The writer only advances when a new unique value is found. This separation is clean and easy to verify mentally.
- **No unnecessary variables or data structures.** You respected the O(1) space constraint naturally — there's no temptation toward a HashSet or auxiliary array here. That shows you understood the constraint, not just the algorithm.
- **All 8 tests pass**, including edge cases like single element, all duplicates, already unique, and negative values.

## 3. What Needs Improvement

- **Comparison target: `nums[writer - 1]` vs `nums[reader - 1]`.** Your comparison on line 23 checks `nums[writer - 1] != nums[reader]`. This works because the writer position always holds the last unique value written. However, the more conventional form is `nums[reader - 1] != nums[reader]` — "is the current element different from the previous one?" This is arguably easier to reason about because it's purely a property of the input scan, independent of the write position. Both are correct, but the `reader - 1` version requires less mental context to verify. In an interview, using `nums[reader - 1]` would let you explain: "I'm scanning for transitions — whenever the value changes, I write it." That's a one-sentence explanation.

- **Extra space in `return  writer;` (line 30).** Tiny cosmetic issue — there's a double space before `writer`. Wouldn't matter in an interview, but in production code, it's the kind of thing a linter would flag.

- **`while` loop vs `for` loop.** A `for` loop would be slightly more idiomatic here since `reader` simply increments from 1 to `nums.length - 1` with no complex control flow. `for (int reader = 1; reader < nums.length; reader++)` would communicate "this is a linear scan" at a glance and eliminate the manual `reader++` at the bottom of the loop. The `while` loop isn't wrong, but it invites a reader to wonder "is there a `continue` or conditional increment hiding somewhere?"

## 4. Correctness Review

**Fully correct.**

Let's trace through Example 2 — `[0, 0, 1, 1, 1, 2, 2, 3, 3, 4]`:

```
Initial:    reader=1, writer=1
            nums = [0, 0, 1, 1, 1, 2, 2, 3, 3, 4]

reader=1:   nums[0]=0 == nums[1]=0 → skip.         writer=1
reader=2:   nums[0]=0 != nums[2]=1 → write.         nums[1]=1, writer=2
reader=3:   nums[1]=1 == nums[3]=1 → skip.         writer=2
reader=4:   nums[1]=1 == nums[4]=1 → skip.         writer=2
reader=5:   nums[1]=1 != nums[5]=2 → write.         nums[2]=2, writer=3
reader=6:   nums[2]=2 == nums[6]=2 → skip.         writer=3
reader=7:   nums[2]=2 != nums[7]=3 → write.         nums[3]=3, writer=4
reader=8:   nums[3]=3 == nums[8]=3 → skip.         writer=4
reader=9:   nums[3]=3 != nums[9]=4 → write.         nums[4]=4, writer=5

return 5 ✓
nums = [0, 1, 2, 3, 4, ...] ✓
```

All edge cases are handled correctly:
- **Single element `[1]`:** Loop never executes (reader=1 is not < 1), returns writer=1. Correct.
- **All duplicates `[7, 7, 7, 7]`:** Reader scans through but writer never advances. Returns 1. Correct.
- **Already unique `[1, 2, 3, 4, 5]`:** Every comparison triggers a write, writer tracks reader. Returns 5. Correct.

## 5. Java-Specific Review

- **Compiles: Yes.** Clean compilation, no warnings.
- **No imports needed.** Pure array manipulation — no collections, no utilities. Clean.
- **Method signature matches LeetCode exactly.** `public int removeDuplicates(int[] nums)` — correct return type, correct parameter. Good discipline.
- **No unnecessary object creation.** No autoboxing, no wrapper types, no auxiliary arrays. This is efficient, idiomatic Java for array problems.
- **Array access is safe.** `writer - 1` is always >= 0 (since writer starts at 1 and only increases), and `reader` is always < `nums.length` (loop guard). No risk of `ArrayIndexOutOfBoundsException`.
- **The double space in `return  writer;`** would be flagged by most formatters (Checkstyle, IntelliJ's default formatter). Minor, but worth noting for code hygiene.

## 6. Time and Space Complexity Review

- **Time Complexity: O(n)** — Single pass through the array. Each element is visited exactly once by the reader. Each comparison and potential write is O(1). Correct and optimal.
- **Space Complexity: O(1)** — Only two integer variables (`reader`, `writer`) beyond the input array. No auxiliary data structures. Correct and optimal.

This is **optimal.** Every element must be examined at least once to determine if it's unique, so O(n) time is a lower bound. The in-place constraint is satisfied with O(1) extra space. There is no asymptotically better approach.

## 7. Pattern / Concept Assessment

**Pattern:** Two Pointers (reader/writer variant, also known as slow/fast for in-place array compaction).

**Assessment: Strong.**

You demonstrated clear understanding of the reader/writer two-pointer pattern. The key insight — that a sorted array has all duplicates adjacent, so a single scan can compact unique values to the front — is fully reflected in your implementation. Your naming (`reader`/`writer`) shows you understand the *roles* of the pointers, not just their positions. This pattern appears frequently in interview problems (L27 Remove Element, L80 Remove Duplicates II, L283 Move Zeroes), and your implementation here shows you could adapt it to those variants with confidence.

## 8. Interview Readiness Feedback

- **Understanding depth:** Strong. You clearly understand why the algorithm works, not just what it does. The choice to start both pointers at 1 (skipping the guaranteed-unique first element) shows careful thought.
- **Pattern matching vs reasoning:** This reads like genuine reasoning. The reader/writer naming convention isn't the "standard" LeetCode answer (most use `i`/`j`), which suggests you thought about the roles rather than memorizing a template.
- **Confidence in Java:** Solid. The code is simple, direct, and takes advantage of primitive array operations. No unnecessary complexity.
- **Interviewer trust:** High. This solution is easy to verify at a glance. An interviewer would quickly confirm correctness and move on to follow-ups.
- **Coding style impression:** "Deliberate, clean, names things well." The kind of code that says "I can be trusted with a production codebase."

## 9. Best Next Improvement

**Use a `for` loop instead of `while` when the iteration variable has a simple linear progression.** This is a general Java idiom issue, not specific to this problem. When a pointer always increments by 1 with no conditional skips or breaks, a `for` loop communicates that invariant structurally. It also localizes the increment, reducing the cognitive load on someone reading the loop body. In an interview, `for` loops signal "straightforward scan" while `while` loops signal "complex control flow" — match the signal to the reality.

## 10. Polished Version

Your solution is already strong. Here's a polished version with the minor refinements:

```java
package com.penrose.leetcode.twopointers;

/**
 * 26. Remove Duplicates from Sorted Array
 * Pattern: Two Pointers
 */
public class L26RemoveDuplicatesFromSortedArray {

    public int removeDuplicates(int[] nums) {
        int writer = 1;

        for (int reader = 1; reader < nums.length; reader++) {
            if (nums[reader] != nums[reader - 1]) {
                nums[writer] = nums[reader];
                writer++;
            }
        }

        return writer;
    }
}
```

**What changed:**
- **`while` loop → `for` loop** — makes the linear scan explicit and localizes the `reader` increment. `reader` is now scoped to the loop.
- **Comparison changed to `nums[reader] != nums[reader - 1]`** — reads as "current differs from previous," which is slightly more intuitive. The reader scan is now self-contained; you don't need to think about where `writer` is to understand the comparison.
- **Removed double space in `return writer;`.**
- Core logic is identical — this is purely a readability polish.

## 11. Coaching Note

This is a confident, clean solution. What stands out is your naming — calling the pointers `reader` and `writer` instead of `i` and `j` or `slow` and `fast` tells me you're thinking about what the code *means*, not just what it *does*. That's a habit that scales really well as problems get harder. You're building a solid two-pointer foundation here, and the progression from stack problems to this shows you're comfortable switching between different patterns. The reader/writer variant you used here is one of the most versatile tools in the interview toolkit — you'll see it again in Remove Element, Move Zeroes, and Remove Duplicates II. You're in great shape to tackle those next.

## 12. Drill Recommendation

1. **L27 Remove Element** — Same reader/writer pattern, but without the sorted guarantee. Tests whether you truly own the pattern or were relying on the sorted property.
2. **L283 Move Zeroes** — Reader/writer with an added constraint (preserve relative order of non-zero elements). A natural next step.
3. **L80 Remove Duplicates from Sorted Array II** — The direct sequel. Allows each element to appear *at most twice*. Forces you to generalize the comparison logic you used here.
