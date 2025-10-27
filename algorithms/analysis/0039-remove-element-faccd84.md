---
title: Remove Element
commit: faccd84
url: https://github.com/josimar-silva/kaizen/commit/faccd84cd89e9ec1a923c7729890cb71318e120c
---

#### Problem
Given an integer array `nums` and an integer `val`, remove all occurrences of `val` in `nums` in-place. The order of the elements may be changed. Then return the number of elements in `nums` which are not equal to `val`.

---

#### Big O Analysis

**Time Complexity:**
- We iterate through the input array of N elements a single time with a "fast" pointer.
- Inside the loop, we perform a constant number of operations (a comparison, an assignment, and an increment).
- ⇒ **O(N)**

**Space Complexity:**
- We modify the array in-place.
- We use a few variables (pointers/counters), which occupy a constant amount of memory regardless of the input array size.
- ⇒ **O(1)**

---

#### Layman’s Terms

- For small inputs (e.g., 100 elements): The algorithm is nearly instantaneous, performing roughly 100 operations.
- For medium inputs (e.g., 10,000 elements): It's still very fast, scaling linearly. It performs about 10,000 operations.
- For large inputs (e.g., 1,000,000 elements): It remains highly practical, taking a million steps. This is very efficient.

👉 **Rule of thumb:** *If the input size doubles, the number of operations doubles. The cost grows directly and predictably with the input size.*

---

#### Conclusion

- **Efficiency:** The two-pointer solution is optimally efficient for this problem, meeting the ideal O(N) time and O(1) space complexity.
- **Limits:** The algorithm has no significant limits for typical array sizes and will perform well into millions of elements.
- **Improvements:** No further algorithmic improvements are possible as we must visit every element at least once.
- **Lesson Learned:** For problems requiring in-place array modifications where some elements are discarded, the "fast and slow pointer" technique is a powerful pattern. It avoids the O(N log N) cost of sorting and the O(N) cost of extra space, providing a clean and optimal solution.
---