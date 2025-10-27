---
title: "Climbing Stairs: A Journey Through Dynamic Programming"
commit: d0c2f0a
url: https://github.com/josimar-silva/kaizen/commit/d0c2f0aab01310291cabafa9d559724f8289bc1e
---

#### Problem
You are climbing a staircase with `n` steps. You can climb either 1 or 2 steps at a time. The goal is to determine the total number of distinct ways you can reach the top.

---

#### The Journey to an Optimal Solution

This problem is a classic introduction to Dynamic Programming (DP). The path to the most efficient solution reveals a common and powerful pattern in algorithm design: moving from a slow, brute-force approach to a highly optimized one.

##### Approach 1: Brute-Force Recursion

The first insight is recognizing the problem's recursive nature. To reach step `n`, you must have come from either step `n-1` (by taking a 1-step jump) or step `n-2` (by taking a 2-step jump). Therefore, the total number of ways to reach step `n` is the sum of the ways to reach the two preceding steps:

`ways(n) = ways(n-1) + ways(n-2)`

This is the Fibonacci sequence.

- **Layman’s Terms:** This is like exploring every possible path up the stairs. For each step, you create two new "universes": one where you take one step, and one where you take two. This creates a massive, branching tree of possibilities.
- **Time Complexity: `O(2^n)`**. The number of calculations roughly doubles for each additional step, leading to exponential growth. This becomes prohibitively slow for `n > 40`.
- **Space Complexity: `O(n)`**. This is determined by the maximum depth of the recursion call stack.

##### Approach 2: Memoization (Top-Down DP)

The brute-force approach is slow because it recalculates the same subproblems repeatedly (e.g., `ways(5)` is calculated multiple times when finding `ways(7)`). We can eliminate this redundant work by caching results.

- **Layman’s Terms:** This is like solving the problem on a whiteboard. Once you calculate the answer for `ways(5)`, you write it down. The next time you need it, you just look it up instead of re-calculating. This technique is called **memoization**.
- **Time Complexity: `O(n)`**. Each subproblem (from 1 to `n`) is now solved only once.
- **Space Complexity: `O(n)`**. We use a map or array to store the results of the `n` subproblems.

##### Approach 3: Tabulation (Bottom-Up DP)

Instead of starting from the top (`n`) and recurring downwards, we can solve the problem iteratively, starting from the bottom.

- **Layman’s Terms:** This is like building the solution step-by-step. You start by writing down the answers for step 1 (1 way) and step 2 (2 ways). Then, you use those to calculate the answer for step 3 (3 ways), and so on, filling a table (`tabulation`) until you reach `n`.
- **Time Complexity: `O(n)`**. A single loop runs from 1 to `n`.
- **Space Complexity: `O(n)`**. An array of size `n` is used to store the results for each step.

##### Approach 4: Space-Optimized Bottom-Up

This is the final and most optimal solution. We observed that to calculate the answer for the current step, we only need the answers for the previous two steps.

- **Layman’s Terms:** Instead of keeping a full record of every step, you only need to remember the last two. It’s like a "sliding window" of two numbers. You calculate the next number, then slide the window forward by discarding the oldest number.
- **Time Complexity: `O(n)`**. The single loop remains.
- **Space Complexity: `O(1)`**. We only need two variables to store the previous two results, regardless of how large `n` is.

---

#### Alternative: The Mathematical Shortcut (Binet's Formula)

For completeness, we also explored a closed-form mathematical solution called Binet's Formula. This formula can calculate the n-th Fibonacci number directly, without recursion or iteration.

- **How it Works:** It uses the golden ratio (`φ`) to jump directly to the answer for a given `n`. This makes it computationally `O(1)`.
- **The Trade-Off:** While it's the fastest in terms of time complexity, it relies on floating-point arithmetic to handle irrational numbers. This can introduce small precision errors that may lead to an incorrect final integer, making it less reliable for programming challenges that require exact integer answers. It's a fascinating mathematical approach but less practical for typical engineering use cases compared to the iterative DP solution.

---

#### Conclusion

- **Efficiency:** The space-optimized bottom-up solution is extremely efficient and is the standard expected answer in an interview setting. It can handle the constraint of `n=45` instantly.
- **Key Takeaway:** This problem is a perfect illustration of the Dynamic Programming workflow. The journey from a simple but slow recursion to a highly optimized iterative solution is a fundamental pattern in computer science. Recognizing the opportunity to trade a small amount of memory (or none at all) to drastically reduce computation time is the core lesson.
