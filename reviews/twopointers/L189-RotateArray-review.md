# L189 Rotate Array — Code Review

**Date:** 2026-03-25 3:40 PM CDT

## Solution Reviewed

```java
package com.penrose.leetcode.twopointers;

import java.util.Arrays;

/**
 * 189. Rotate Array
 * Pattern: Two Pointers
 * <p>
 * Given an integer array nums, rotate the array to the right by k steps.
 * Must be done in-place with O(1) extra memory.
 * <p>
 * Constraints:
 * 1 <= nums.length <= 10^5
 * -2^31 <= nums[i] <= 2^31 - 1
 * 0 <= k <= 10^5
 */
public class L189RotateArray {

    public void rotate(int[] nums, int k) {

        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            left++;
            right--;
        }

        left = 0;
        //why - 1?
        right = k % nums.length - 1;

        while (left < right) {
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            left++;
            right--;
        }

        left = right = k % nums.length;
        right = nums.length - 1;

        while (left < right) {
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            left++;
            right--;
        }

    }
}
```

## 1. Overall Impression

You identified the correct and optimal approach — the three-reverse technique — and implemented it correctly. All 8 test cases pass, including tricky edge cases like `k > length`, `k == 0`, and single-element arrays. That's a strong result. What's holding this solution back from being interview-polished is the **code repetition** — the swap-based reverse logic is copy-pasted three times, which makes the code harder to read, harder to verify, and harder to explain under pressure. Extracting a `reverse` helper would immediately elevate this from "correct" to "clean and confident." There are also a few smaller issues: an unused import, a dead assignment, and a self-questioning comment left in the code. In a real interview, this would likely pass functionally, but the repetition and leftover artifacts might raise a small concern about code discipline.

## 2. What You Did Well

- **Correct algorithm choice.** The three-reverse approach is the textbook optimal solution for in-place O(1) rotation. You didn't reach for an auxiliary array or cyclic replacement — you went straight to the cleanest known approach.
- **Correct handling of `k % nums.length`.** You recognized that when `k >= nums.length`, you need the effective rotation amount. This is a common trap and you handled it.
- **All edge cases pass.** `k = 0`, `k = length` (full rotation), `k > length`, single element — all correct. These are the cases that trip people up, and yours work cleanly.
- **Consistent variable naming.** Using `left` and `right` for the reverse pointers is clear and appropriate for the three-reverse pattern.
- **Correct pointer boundaries for each reversal.** You got the index math right for all three sub-reversals: `[0, n-1]`, `[0, k%n - 1]`, `[k%n, n-1]`. This is the part where people make off-by-one errors, and you didn't.

## 3. What Needs Improvement

- **Code repetition.** The 6-line swap block is duplicated three times. This is the single biggest issue. In an interview, an interviewer sees three identical blocks and wonders: "Did they consider extracting a helper? Do they think about code structure?" A `reverse(int[] nums, int left, int right)` helper would reduce the method body to 4 lines and make the algorithm's structure immediately visible.

- **Unused import on line 3: `import java.util.Arrays;`.** This is a leftover from debugging (likely used for `Arrays.toString()` to print the array). Leaving unused imports in submitted code is a small but noticeable sloppiness signal.

- **Dead assignment on line 44: `left = right = k % nums.length;`.** The assignment to `right` is immediately overwritten on line 45 with `right = nums.length - 1`. Writing `left = right = ...` when you only need `left = ...` creates confusion — a reader pauses to figure out if `right` matters here, only to discover it doesn't. Just write `left = k % nums.length;`.

- **Self-questioning comment on line 33: `//why - 1?`.** Leaving a "why?" comment in your solution tells an interviewer you don't fully understand your own code. Either answer the question for yourself and remove the comment, or replace it with a brief explanatory comment. The answer: `right` is an *inclusive* index — you want to reverse elements `[0]` through `[k%n - 1]`, so the last index in that range is `k%n - 1`.

- **Repeated computation of `k % nums.length`.** It appears on lines 34, 44, and implicitly you should be thinking of it as a single value. Computing it once at the top (`int effectiveK = k % nums.length;`) would be cleaner and signal intentional thinking.

## 4. Correctness Review

**Fully correct.**

Let's trace through Example 1 — `nums = [1, 2, 3, 4, 5, 6, 7]`, `k = 3`:

```
Step 1: Reverse entire array [0..6]
  [1,2,3,4,5,6,7] → [7,6,5,4,3,2,1]

Step 2: Reverse first k%7 = 3 elements [0..2]
  right = 3 - 1 = 2
  [7,6,5,4,3,2,1] → [5,6,7,4,3,2,1]

Step 3: Reverse remaining elements [3..6]
  left = 3, right = 6
  [5,6,7,4,3,2,1] → [5,6,7,1,2,3,4] ✓
```

Edge case — `k = 0`, `nums = [1, 2, 3]`:

```
Step 1: Reverse all → [3, 2, 1]
Step 2: right = 0 - 1 = -1, left = 0, 0 < -1 is false → no-op ✓
Step 3: left = 0, right = 2 → reverses back to [1, 2, 3] ✓
```

Edge case — `k = 5`, `nums = [1, 2, 3]` (k > length):

```
k % 3 = 2
Step 1: Reverse all → [3, 2, 1]
Step 2: right = 1, reverse [0..1] → [2, 3, 1]
Step 3: left = 2, right = 2 → no-op ✓
Expected: [2, 3, 1] ✓
```

All edge cases are handled correctly by the natural behavior of the loop guards.

## 5. Java-Specific Review

- **Compiles: Yes**, aside from the unused import warning.
- **Unused import: `java.util.Arrays`** — this would trigger a compiler warning and would be flagged by IntelliJ's inspections. Remove it.
- **Method signature matches LeetCode exactly.** `public void rotate(int[] nums, int k)` — correct.
- **No unnecessary object creation.** Pure primitive operations. O(1) space is genuinely achieved.
- **The dead assignment `left = right = k % nums.length`** compiles fine but is misleading. Java evaluates right-to-left in chained assignment, so `right` gets the value and then `left` gets the same value. But `right` is overwritten on the next line, making the chained form pointless. Just use `left = k % nums.length;`.
- **Helper method extraction.** In idiomatic Java, when you have three identical blocks of logic, the strong convention is to extract a private method. This isn't just about DRY — it's about making the code scannable. An interviewer reading `reverse(nums, 0, n - 1); reverse(nums, 0, k - 1); reverse(nums, k, n - 1);` can verify the algorithm in seconds.

## 6. Time and Space Complexity Review

- **Time Complexity: O(n).** Each of the three reversals processes a portion of the array, and the total elements touched across all three reversals is exactly `2n` (each element is swapped exactly twice total). This is optimal — you must touch every element at least once.
- **Space Complexity: O(1).** Only a fixed number of integer variables (`left`, `right`, `temp`). No auxiliary arrays. Meets the problem constraint.

This is **optimal.** The three-reverse technique is the standard O(n) time, O(1) space solution. The only alternative with the same complexity is the cyclic replacement approach, which is harder to implement correctly and harder to explain.

## 7. Pattern / Concept Assessment

**Pattern:** Two Pointers (reversal variant — using opposing pointers to reverse subarrays as building blocks for a larger transformation).

**Assessment: Developing.**

You correctly identified that the three-reverse technique solves this problem and got the index boundaries right — that's the hard part. However, the comment `//why - 1?` suggests the *reason* behind the index math isn't fully internalized yet. The key mental model is: a reversal on `[left, right]` uses inclusive indices, so to reverse the first `k` elements, `right` must be `k - 1` (the last index in that range, not the count). Once that clicks, the `-1` becomes obvious rather than surprising. You're close — the understanding is there mechanically, but it needs to become intuitive. A few more reversal-based problems will lock it in.

## 8. Interview Readiness Feedback

- **Understanding depth:** Good but not complete. You clearly know the three-reverse algorithm works, but the `//why - 1?` comment suggests you're still building full intuition for *why* each boundary is set the way it is. An interviewer who spots that comment would probe: "Can you explain why it's `k - 1` and not `k`?"
- **Pattern matching vs reasoning:** This reads like pattern matching with incomplete reasoning. You knew the algorithm to apply (great), but the self-questioning comment and code repetition suggest the implementation was assembled piece by piece rather than flowing from a clear mental model.
- **Confidence in Java:** Adequate but not polished. The unused import, dead assignment, and code repetition give a "still debugging" impression rather than a finished solution.
- **Interviewer trust:** Moderate. The solution is correct, which matters most. But an interviewer would likely ask you to extract the helper and explain the index logic — be ready for that.
- **Coding style impression:** "Gets to the right answer, but could clean up before submitting." The impression is of someone who solves the problem correctly but doesn't take the final pass to polish.

## 9. Best Next Improvement

**Extract a `reverse` helper method.** This is the single change that would most improve both your code quality and your interview presentation. When you write `reverse(nums, 0, n - 1)`, `reverse(nums, 0, k - 1)`, `reverse(nums, k, n - 1)`, the algorithm becomes a three-line story that you can explain in one sentence: "Reverse all, reverse first k, reverse the rest." That clarity is what separates a correct answer from a confident one.

## 10. Polished Version

```java
package com.penrose.leetcode.twopointers;

/**
 * 189. Rotate Array
 * Pattern: Two Pointers
 */
public class L189RotateArray {

    public void rotate(int[] nums, int k) {
        int n = nums.length;
        k = k % n;

        reverse(nums, 0, n - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, n - 1);
    }

    private void reverse(int[] nums, int left, int right) {
        while (left < right) {
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            left++;
            right--;
        }
    }
}
```

**What changed:**

- **Extracted `reverse` helper** — the three-reverse algorithm is now three lines. The structure of the solution is immediately visible: reverse all, reverse first k, reverse the rest. This is what an interviewer wants to see.
- **Computed `k = k % n` once at the top** — avoids repeated modulo computation and makes the effective rotation amount explicit.
- **Removed unused `import java.util.Arrays`.**
- **Removed dead assignment `left = right = ...`.**
- **Removed self-questioning comment** — the index logic is now clear from the helper's parameter names (`left`, `right` — both inclusive).
- Core algorithm is identical — this is purely a structural and clarity polish.

## 11. Coaching Note

You got the hardest part right — you chose the optimal algorithm and nailed the index boundaries on all three reversals. That's not trivial; the off-by-one errors on this problem trip up a lot of people, and your solution handles every edge case correctly. The next level for you is presentation: taking a correct solution and making it *visibly* correct. When an interviewer reads three calls to `reverse()` with clear boundaries, they can verify your logic in five seconds. When they read three inlined swap loops, they have to trace each one separately. Same algorithm, same complexity, but very different impression. You're clearly building real understanding of the two-pointer pattern — the L26 and L283 solutions showed clean reader/writer usage, and now you've added the reversal variant. Keep building this toolkit one pattern at a time. You're making solid progress.

## 12. Drill Recommendation

1. **Practice the reverse helper as muscle memory.** Write `reverse(int[] arr, int left, int right)` from scratch five times without looking. It should be as automatic as writing a for-loop. This helper shows up in rotation, palindrome, and partition problems — you want it to be instant.
2. **L151 Reverse Words in a String** — Another three-reverse problem. Reverse all characters, then reverse each word. Reinforces the same building-block concept with a string twist.
3. **Dry-run the index math by hand.** Take `[1,2,3,4,5]` with `k = 2` and write out each reversal step on paper, explicitly labeling the inclusive `[left, right]` range. Do this until `k - 1` as a boundary feels obvious, not surprising.
