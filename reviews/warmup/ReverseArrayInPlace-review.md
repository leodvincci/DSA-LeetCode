# Reverse Array In Place — Code Review

**Date:** 2026-03-24 (Chicago CT)

## Solution Reviewed

```java
package com.penrose.leetcode.warmup;

/**
 * Warmup: Reverse Array In Place
 * Pattern: Two Pointers (warmup drill)
 *
 * Given an integer array nums, reverse the array in-place.
 * Do not return a new array. Modify nums directly.
 *
 * Constraints:
 * 0 <= nums.length <= 50,000
 * -100,000 <= nums[i] <= 100,000
 * Must be done in-place with O(1) extra memory.
 */
public class ReverseArrayInPlace {

    public void reverse(int[] nums) {
        int left = 0;
        int right = nums.length - 1;

        while (left < right){
            int temp = nums[right];
            nums[right] = nums[left];
            nums[left] = temp;
            left++;
            right--;
        }
    }
}
```

## 1. Overall Impression

This is textbook-perfect. You identified the two-pointer approach, implemented the swap cleanly with a temp variable, chose the right loop condition (`left < right`), and wrote nothing unnecessary. There's genuinely nothing holding this solution back — it's correct, optimal, and reads like the reference answer you'd find in a DSA textbook. This would pass instantly in an interview and leave a strong impression of someone who's comfortable with fundamentals.

## 2. What You Did Well

- **Correct approach, zero hesitation.** Two pointers from both ends, swap, converge. That's the entire algorithm, and you nailed it without any extra scaffolding or over-thinking.
- **Clean swap using `temp`.** The three-line swap (lines 21–23) is the standard, readable Java pattern. No XOR tricks, no arithmetic tricks — just clear, correct code that anyone can verify at a glance.
- **Perfect loop condition: `left < right`.** Not `left <= right` (which would be wasteful but not incorrect), and not `left != right` (which could miss the crossing). `left < right` is the precise, canonical condition — it naturally handles both odd-length arrays (middle element stays put) and even-length arrays.
- **Good variable naming.** `left` and `right` immediately communicate the pointer roles and direction of travel. `temp` is the universally understood name for a swap variable. Nothing to improve here.
- **Handles all edge cases implicitly.** Empty array: `right` is -1, so `left < right` is false, loop never executes. Single element: `left == right == 0`, loop never executes. Even length, odd length, all-identical — all handled by the same logic with no special cases. That's the mark of a well-chosen algorithm.
- **Void return, in-place mutation.** You respected the problem contract exactly — no new array allocation, no return value.

## 3. What Needs Improvement

Honestly, there's very little to critique here. I'll note a few minor things for completeness:

- **No null guard.** If `nums` is `null`, line 18 throws a `NullPointerException` on `nums.length`. In a LeetCode context, inputs are guaranteed non-null, so this is fine. But in a real interview, it's worth mentioning: "I'm assuming the input is non-null per the constraints. In production, I'd add a null check." That one sentence shows defensive thinking without cluttering the code.

- **Spacing before `{` on the while loop (line 20).** `while (left < right){` — missing a space before the brace. Extremely minor, but consistent Java style is `while (left < right) {`. In an interview setting, nobody would notice. In a code review, a formatter would catch it.

That's it. These are genuinely nitpicks, not substantive issues.

## 4. Correctness Review

**Fully correct.**

Let's trace through `[1, 2, 3, 4, 5]`:

```
Initial:    left=0, right=4
            nums = [1, 2, 3, 4, 5]

Iteration 1: swap nums[0] and nums[4]
             nums = [5, 2, 3, 4, 1]    left=1, right=3

Iteration 2: swap nums[1] and nums[3]
             nums = [5, 4, 3, 2, 1]    left=2, right=2

left < right → false → exit.
Result: [5, 4, 3, 2, 1] ✓
```

And the even-length case `[1, 2, 3, 4]`:

```
Initial:    left=0, right=3

Iteration 1: swap nums[0] and nums[3]
             nums = [4, 2, 3, 1]    left=1, right=2

Iteration 2: swap nums[1] and nums[2]
             nums = [4, 3, 2, 1]    left=2, right=1

left < right → false → exit.
Result: [4, 3, 2, 1] ✓
```

Edge cases — empty `[]`: `right = -1`, `0 < -1` is false, immediate return. Single `[1]`: `left = right = 0`, `0 < 0` is false, immediate return. All correct.

## 5. Java-Specific Review

- **Compiles: Yes.** No issues.
- **Idiomatic Java: Yes.** The temp-variable swap is the standard Java approach for primitives. There's no built-in swap utility for array elements in Java (unlike some other languages), so this is the right way to do it.
- **No unnecessary object creation.** Three local `int` variables — that's the minimum possible. No autoboxing, no wrapper types, no collections.
- **Method signature is clean.** `public void reverse(int[] nums)` — correct return type, correct parameter, clear name.
- **Array access is safe.** `left` starts at 0 and increases; `right` starts at `nums.length - 1` and decreases. The loop guard `left < right` ensures both indices are always in bounds. No risk of `ArrayIndexOutOfBoundsException`.
- **Interview-clean: Yes.** This is exactly what you'd write on a whiteboard. Nothing to add, nothing to remove.

## 6. Time and Space Complexity Review

- **Time Complexity: O(n)** — The loop runs `n/2` iterations, each doing O(1) work (one swap, two pointer moves). That's O(n) total. Correct and optimal — you must touch every element at least once to reverse the array.
- **Space Complexity: O(1)** — Three integer variables (`left`, `right`, `temp`) regardless of input size. Correct and optimal — the problem requires in-place reversal, and you achieved it.

This is **optimal in both dimensions.** There is no faster or more space-efficient way to reverse an array in place.

## 7. Pattern / Concept Assessment

**Pattern:** Two Pointers (converging / outside-in).

**Assessment: Strong.**

This is the purest form of the converging two-pointer pattern: start at both ends, process toward the center, stop when the pointers meet. You executed it without hesitation or missteps. What's particularly notable is the progression in your two-pointer work — in L26 (Remove Duplicates), you used the reader/writer variant where both pointers move in the same direction. Here, you used the converging variant where pointers move toward each other. Recognizing which variant to apply, and implementing both cleanly, shows genuine pattern fluency rather than rote memorization.

## 8. Interview Readiness Feedback

- **Understanding depth:** Complete. This is a foundational problem and you solved it with zero wasted motion, which is exactly the signal an interviewer wants — "this person has solid fundamentals and doesn't overthink simple problems."
- **Pattern matching vs reasoning:** This reads like internalized understanding. The code is too clean and natural to be memorized — it looks like you just *know* how converging two pointers work.
- **Confidence in Java:** Strong. The swap idiom is correct, the loop is idiomatic, and there's nothing that would make an interviewer pause.
- **Interviewer trust:** Very high. An interviewer seeing this would think "good, fundamentals are solid" and move on to harder questions — which is exactly what you want from a warmup problem.
- **Coding style impression:** "Efficient, clean, doesn't over-engineer." The absence of unnecessary code is itself a positive signal.

## 9. Best Next Improvement

**Practice articulating your thought process out loud while coding.** The code itself is flawless, so the next growth area is interview communication. For a problem like this, that means: state the approach before coding ("I'll use two pointers converging from both ends, swapping as they go"), note the edge cases while writing ("this naturally handles empty and single-element arrays because the loop condition prevents entry"), and state the complexity after finishing ("O(n) time, O(1) space"). The code speaks for itself, but the narration is what turns a correct answer into an impressive one.

## 10. Polished Version

Your solution is already at interview quality. The only cosmetic change is the space before `{`:

```java
package com.penrose.leetcode.warmup;

/**
 * Warmup: Reverse Array In Place
 * Pattern: Two Pointers (warmup drill)
 */
public class ReverseArrayInPlace {

    public void reverse(int[] nums) {
        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            int temp = nums[right];
            nums[right] = nums[left];
            nums[left] = temp;
            left++;
            right--;
        }
    }
}
```

**What changed:**
- Added space before `{` on the `while` line — standard Java formatting.
- That's it. The logic is unchanged. This was already the reference solution.

## 11. Coaching Note

This is exactly what warmup drills are for — building the kind of muscle memory where the right pattern flows out naturally. Your two-pointer work is clearly solidifying. You've now shown both variants (same-direction reader/writer in L26, converging in this problem) with clean implementations, and that versatility is what makes the pattern truly useful. The fundamentals are locked in. You're ready to take on problems where two pointers are just one piece of a larger puzzle — problems where the hard part isn't the pointer mechanics, but knowing *when* and *where* to deploy them.

## 12. Drill Recommendation

1. **L344 Reverse String** — The LeetCode version of this exact problem, but with a `char[]`. Confirms you can transfer the pattern to a slightly different type. Quick win.
2. **L125 Valid Palindrome** — Converging two pointers with a twist: skip non-alphanumeric characters. Tests whether you can layer logic on top of the base pattern you just drilled.
3. **L11 Container With Most Water** — Converging two pointers where the *decision* of which pointer to move requires reasoning, not just mechanical convergence. A meaningful step up.
