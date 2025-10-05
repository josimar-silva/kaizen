---
title: Longest Palindromic Substring
commit: 8566cb2
url: https://github.com/josimar-silva/kaizen/commit/8566cb244f329c75f7f00adece35d6ac982e96a1
---

#### Problem
Given a string `s`, return the longest palindromic substring in `s`. A string is a palindrome if it reads the same forwards and backwards.

---

#### Big O Analysis
Two primary approaches were developed and analyzed:

**1. Brute-Force Solution**
- **Time Complexity:** O(N^3)
  - Two nested loops generate all `O(N^2)` possible substrings.
  - For each substring, a palindrome check (e.g., reversing the string) takes up to `O(N)` time.
  - ⇒ **O(N^2 * N) = O(N^3)**
- **Space Complexity:** O(N^2)
  - The implementation stored all `O(N^2)` substrings in a list before filtering.

**2. Dynamic Programming Solution**
- **Time Complexity:** O(N^2)
  - The algorithm fills an `N x N` table.
  - Each cell is computed in constant time `O(1)` using the results of a previous subproblem.
  - ⇒ **O(N^2)**
- **Space Complexity:** O(N^2)
  - Requires an `N x N` table to store the results of whether any given substring is a palindrome.

---

#### Layman’s Terms
- **Brute-Force:** This method is like taking every possible snippet of the string, from length 1 up to the full string, and checking each one to see if it's a palindrome. It's thorough but very slow because it re-checks work constantly.
- **Dynamic Programming:** This method is smarter. It builds a big checklist (an N x N table) that answers "is the substring from here to there a palindrome?". It starts with small substrings (length 1 and 2) and uses those answers to figure out the answers for bigger substrings, avoiding re-checking.

👉 **Rule of thumb:** *For a string of 1,000 characters:*
- The **Brute-Force** method would do roughly a **billion** operations (`1000^3`), which is too slow.
- The **Dynamic Programming** method would do about a **million** operations (`1000^2`), which is fast and acceptable.

---

#### Conclusion

- **Efficiency:** The `O(N^3)` brute-force solution is too slow for the problem's constraints (`N <= 1000`). The `O(N^2)` dynamic programming solution is efficient and a standard approach.
- **Limits:** The DP solution's `O(N^2)` space complexity (e.g., `1000x1000` table) is its main drawback, though manageable for the given constraints.
- **Improvements:** An even better solution exists that achieves **O(N^2) time** with **O(1) space**. This method, often called "Expand from Center," iterates through each character and expands outwards to find the longest palindrome centered at that position. A further, highly advanced improvement is **Manacher's Algorithm**, which can solve the problem in **O(N) linear time**. It's a complex algorithm that cleverly avoids re-checking characters by using an auxiliary array to store the radius of palindromes centered at each position, but it is the most optimal solution in terms of time complexity.
- **Lesson Learned:** This session was a journey from a naive, slow algorithm to a more complex but far more efficient one. It highlights the importance of complexity analysis in identifying performance bottlenecks and demonstrates how Dynamic Programming can solve problems by building upon the solutions to smaller, overlapping subproblems.
