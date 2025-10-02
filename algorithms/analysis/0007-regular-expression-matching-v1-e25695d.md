---
title: Regular Expression Matching v1
commit: e25695d
url: https://github.com/josimar-silva/kaizen/commit/e25695d646863007dd48a0cffa001397e80c915c
---

#### Problem
Given an input string `s` and a pattern `p`, implement regular expression matching with support for `.` and `*`, where `.` matches any single character and `*` matches zero or more of the preceding element. The matching should cover the entire input string.

---

#### Big O Analysis

**Time Complexity:**  
- The solution uses recursion with memoization (top-down dynamic programming).
- The state of each subproblem is defined by the current suffixes of the string `s` and pattern `p`.
- Let `S` be the length of `s` and `P` be the length of `p`. There are `S * P` possible unique subproblems.
- Thanks to the cache, each subproblem is computed only once.
- ⇒ **O(S * P)**

**Space Complexity:**  
- The space complexity is determined by the recursion depth and the size of the memoization cache.
- The recursion depth can be up to `S + P` in the worst case.
- The cache can store up to `S * P` key-value pairs.
- ⇒ **O(S * P)**

---

#### Layman’s Terms

- For small inputs, like a string and pattern of length 5, the algorithm is very fast.
- For the given constraints (lengths up to 20), the number of operations would be at most around `20 * 20 = 400`, which is very small.
- The algorithm builds a solution by breaking the problem down into smaller, overlapping subproblems and storing their results to avoid re-computation.

👉 **Rule of thumb:** *The execution time and memory usage grow quadratically with the lengths of the string and pattern.*

---

#### Conclusion

- **Efficiency:** This solution is efficient enough for the given constraints. For much larger inputs, the `O(S * P)` complexity might become an issue.
- **Limits:** The recursive nature of the solution could lead to a stack overflow for very long strings/patterns, but this is not an issue with the given constraints.
- **Improvements:** A bottom-up dynamic programming approach using a 2D table would also have the same time and space complexity but would avoid recursion, which can sometimes be slightly more efficient in practice. The logic would be very similar.
- **Lesson Learned:** This problem is a classic example of how dynamic programming (or memoization) can be used to optimize a problem that would otherwise have an exponential time complexity. It demonstrates the power of storing and reusing the results of subproblems.

---
