---
title: First Bad Version
commit: 469cf3f
url: https://github.com/josimar-silva/kaizen/commit/469cf3f295a5d9217967ff0803ade697d05ec9f2
---

#### Problem
Find the first bad version in a sequence of `n` versions `[1, 2, ..., n]`. A given API `bool isBadVersion(version)` returns `true` if the version is bad. 
All versions after a bad version are also bad. 
The goal is to return the number of the first bad version, minimizing the number of calls to the `isBadVersion` API.

---

#### Big O Analysis

**Time Complexity:**  
- The algorithm uses a binary search approach, which effectively halves the search space in each step.
- Each iteration involves a constant number of operations: calculating the midpoint, calling `isBadVersion`, and adjusting `left` or `right` pointers.
- The number of iterations required to narrow down the search space from `N` to `1` is logarithmic.
- ⇒ **O(log N)**, where `N` is the total number of versions.

**Space Complexity:**  
- The algorithm utilizes a fixed number of variables (`left`, `right`, `midPosition`) to manage the search boundaries.
- The memory usage remains constant regardless of the number of versions `N`.
- ⇒ **O(1)**

---

#### Layman’s Terms

Imagine you have a very long list of product versions, numbered 1 to `N`, and you know that somewhere in the middle, a version broke, and every version *after* it is also broken. You have a special tool (`isBadVersion`) that can instantly tell you if a specific version is broken, but you want to use it as few times as possible.

-   The algorithm works like a detective searching for a specific page in a massive book. Instead of checking every page one by one:
    1.  You open the book exactly in the middle.
    2.  You check that page. If it's a "bad" page, you know the *first* bad page must be that one or somewhere before it. So, you discard the second half of the book and focus on the first half.
    3.  If it's a "good" page, you know the first bad page must be somewhere after it. So, you discard the first half and focus on the second half.
    4.  You keep repeating this process – always jumping to the middle of your remaining section – until you've narrowed it down to just one page.

👉 **Rule of thumb:** *What happens when input doubles?*
-   If you double the number of versions, the algorithm only needs to make one additional check. For instance, finding the first bad version among 1,000,000,000 versions takes only about 30 checks. This is incredibly efficient compared to checking every version.

---

#### Conclusion

-   **Efficiency:** The binary search approach achieves optimal **O(log N) time complexity** and **O(1) space complexity**. This is highly efficient and perfectly suited for the problem's constraints, especially the requirement to minimize API calls when `N` can be very large.
-   **Limits:** The solution is robust and handles the specified constraints without practical limits within standard integer ranges.
-   **Improvements:** The binary search method is the most optimal algorithmic approach for this problem. Further improvements would likely involve minor micro-optimizations within the language or environment rather than fundamental algorithmic changes.
-   **Lesson Learned:** This problem is a classic illustration of how **binary search** can be applied to find a "transition point" (the switch from good to bad) in a monotonically ordered sequence. It emphasizes the power of exploiting inherent problem properties (the sorted nature of versions and the "all subsequent are bad" rule) to achieve significant performance gains, transforming a potentially linear-time task into a highly efficient logarithmic-time one. It also highlights the importance of precise pointer management and loop conditions to avoid common off-by-one errors in binary search implementations.