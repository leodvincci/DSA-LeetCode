# L121 — Best Time to Buy and Sell Stock — Solution Review

**Review date:** Friday, March 27, 2025 — US Central Time (`America/Chicago`)

**Code reviewed:** `src/main/java/com/penrose/leetcode/kadanes/L121BestTimeToBuyAndSellStock.java`

---

### 1. Overall Impression

You implemented a clear linear scan with two indices that behaves like “always buy at the cheapest day seen so far, and see what profit selling today gives.” The control flow matches the problem’s rule (buy before sell), and the logic is correct on the usual examples. The main things holding this back from feeling “finished” in a repo or interview are polish: the stale `// TODO: implement` comment contradicts working code, and naming could better signal intent (`left`/`right` are easy to misread as a classic two-pointer pair rather than “best buy index” + “current day”). In a real interview, this approach would likely pass if you explained the invariant (“`left` is the index of the minimum price in `prices[0..right-1]` after updates”) and walked an example once.

---

### 2. What I Did Well

- **Correct core idea:** One pass, reset the buy position when you see a new low, otherwise update max profit with `prices[right] - prices[left]`.
- **Efficient:** Single `while` loop, constant extra space—appropriate for the constraints.
- **Readable structure:** The `if / else` split is easy to follow: “new cheapest day” vs “try selling today.”
- **Safe use of indices:** `right` starts at `1`, so you never “sell” before you’ve established a buy day when length ≥ 2; length `1` skips the loop and returns `0`, which matches the problem.
- **Good use of `Math.max`:** Keeps the profit update concise and idiomatic.

---

### 3. What Needs Improvement

- **Stale TODO:** Line 10 still says `// TODO: implement` even though the method is implemented—this reads as unfinished in reviews and PRs.
- **Naming vs mental model:** `left` and `right` suggest symmetric two-pointer techniques (e.g., toward the middle). Here, `right` is really “current day” and `left` is “best buy day so far.” Names like `buy` / `day` or `minPriceIndex` / `i` match the story better in interviews.
- **Defensive habits (interview bonus):** LeetCode guarantees `prices.length >= 1`, but interviewers sometimes ask about `null` or empty arrays; a one-line guard (or stating your assumption aloud) shows maturity.
- **Minor style:** Prefer spaces after keywords for typical Java style (`if (` not `if(`) for consistency with common formatters and team codebases.

---

### 4. Correctness Review

**Verdict: Fully correct** for the stated LeetCode constraints.

**Why:** At each `right`, you either:

1. **Move `left` to `right`** when `prices[right]` is strictly less than `prices[left]`, meaning the best buy day in the prefix ending at `right` is `right` itself (you cannot beat that price with an earlier day).
2. **Otherwise** treat `left` as the buy day and `right` as the sell day, since `prices[left]` is the minimum in the relevant prefix for maximizing `sell - buy` when selling on `right`.

**Quick trace — `[7,1,5,3,6,4]`:** After seeing `1`, `left` stays on the cheap day while `right` walks forward; profits considered include `5-1`, `3-1`, `6-1`, `4-1`; max becomes `5`. **Quick trace — `[2,4,1]`:** You record profit `2` before moving `left` to the `1`; you never incorrectly require selling after the final dip if a better profit already happened.

---

### 5. Java-Specific Review

- **Compilation:** As written, this compiles: correct package, `public int maxProfit(int[] prices)` matches LeetCode’s API, types are consistent.
- **Idioms:** `Math.max` and a simple `while` loop are standard and interview-friendly.
- **Null / edge cases:** No NPE from your logic if `prices` is non-null and length ≥ 1 (LeetCode). If `prices` were `null`, you’d NPE on `prices.length`—worth mentioning as an assumption in an interview.
- **Mutability:** You don’t mutate the input array—good default for this problem.
- **Cleaner interview variant:** Some candidates use plain indices `i` and `minPrice` (value) instead of `left` pointing at an index; both are fine; choose whichever you can explain more clearly.

---

### 6. Time and Space Complexity Review

| | |
|---|---|
| **Time complexity** | **O(n)** where `n = prices.length` — single pass. |
| **Space complexity** | **O(1)** — only a few primitives. |

Your solution matches the optimal asymptotics for this problem. The “standard” formulation is often expressed as tracking `minPriceSoFar` and `maxProfit` without naming two pointers; your version is equivalent and equally optimal.

---

### 7. Pattern / Concept Assessment

**Primary patterns:** **Array / greedy single pass**, **“prefix minimum”**, often discussed alongside **dynamic programming (1D state)** on LeetCode. Your folder label **Kadane’s** is related but not the only name interviewers use (see below).

**Your understanding of the pattern:** **Strong** — you implemented a correct O(n) invariant-based scan and avoided the trap of nested loops.

**Reasoning:** You’re effectively maintaining the best buy opportunity seen so far and evaluating each day as a sell day. That’s the same structural insight as many linear “state + scan” array problems.

---

### 8. Interview Readiness Feedback

- **Depth:** If you can state the invariant in one sentence (“`left` indexes the minimum price among all days before `right`, after each update”), you’ll sound like you understand the solution, not just pattern-matched.
- **Java confidence:** The code is simple enough to write live; the risk is **communication**—interviewers may push on *why* moving `left` to `right` is always safe when `prices[right] < prices[left]`.
- **Trust:** A reviewer would likely trust this implementation once the stale TODO is removed and naming clarifies intent.
- **Style impression:** Straightforward and readable; small polish (comments, names, formatting) upgrades perceived professionalism.

---

### 9. Best Next Improvement

**Single priority:** Remove misleading comments and **rename indices** so the code tells the same story as your spoken explanation (buy day vs current day). That one change improves both maintainability and how confidently you present the solution.

---

### 10. Rewrite Suggestion

Your logic is already strong; below is a **polished, interview-style** version: same idea, clearer names, no stale TODO, slightly more conventional spacing.

```java
public int maxProfit(int[] prices) {
    int buyDay = 0;
    int maxProfit = 0;

    for (int day = 1; day < prices.length; day++) {
        if (prices[day] < prices[buyDay]) {
            buyDay = day;
        } else {
            maxProfit = Math.max(maxProfit, prices[day] - prices[buyDay]);
        }
    }
    return maxProfit;
}
```

**What changed and why:** `buyDay` / `day` make the economic meaning obvious; `for` loop is a common Java idiom for “scan each day”; behavior matches your `while` version.

---

### 11. Coaching Note

You picked a linear-time structure that matches how strong candidates think about this problem: never reconsider an old, more expensive buy day once a cheaper one appears. That’s a solid habit for stock-style and “best so far” array problems. The gap now is mostly presentation—making the code read like your reasoning—so the same good thinking shows up instantly to a reviewer or interviewer. Keep building on that clarity; it’s what turns a correct solution into a confident one.

---

## Kadane’s algorithm — what it is, and how it relates to L121

**Kadane’s algorithm** solves: given an array of numbers (often positive and negative), find the **maximum sum of a contiguous subarray**. The classic loop keeps a **running sum** that either **extends** the previous subarray or **starts fresh** at the current element, and tracks the best sum seen.

**How L121 connects:** Define day-to-day **changes** \(d_i = prices[i] - prices[i-1]\) for \(i \ge 1\). Any buy-then-sell pair \((i, j)\) with \(i < j\) has profit \(prices[j] - prices[i] = d_{i+1} + d_{i+2} + \cdots + d_j\) — a **sum of a contiguous subarray** of \(d\). The twist is LeetCode asks for **maximum profit**, and if every contiguous sum were negative, the answer is **0** (you skip trading), whereas vanilla Kadane on \(d\) would return the **least negative** sum. So people say “**Kadane on gains with a floor at 0**” or “reduce to max subarray then `max(0, …)`.”

**Your code** does **not** build the differences array explicitly; it uses the equivalent **greedy / prefix-minimum** view: track the **cheapest buy so far** and the **best profit so far**. That’s the same optimal O(n) idea, just packaged without forming \(d\). Calling it “Kadane’s” in the file header is **defensible** because of that equivalence; in an interview, saying **“single pass, track minimum price seen so far”** is equally standard and often clearer unless the interviewer already brought up max subarray.

---

#### 12. Drill Recommendation

1. **Explain aloud** the invariant for your `left`/`right` (or `buyDay`/`day`) loop in under 20 seconds before coding next time.
2. **L53 Maximum Subarray** in the same `kadanes` folder: implement Kadane explicitly, then verbally relate it to L121 via the differences array (strengthens the “why these problems are cousins” story).
3. **One tricky custom trace** each session (e.g. `[2, 5, 1, 3]`) until pointer/index updates feel automatic without doubt.
