---
title: Container With Most Water
commit: a546912
url: https://github.com/josimar-silva/kaizen/commit/a5469127c01551655557f2e37fd2895c2213e837
---

#### Problem
Given an array of non-negative integers representing vertical lines, find two lines that, together with the x-axis, form a container that holds the most water. The width of the container is the distance between the lines, and the height is limited by the shorter of the two lines.

---

#### Big O Analysis

**Time Complexity:**  
- The algorithm uses a two-pointer approach, with one pointer at the start (`left`) and one at the end (`right`) of the array.
- The pointers move towards each other in a `while` loop, which runs as long as `left < right`.
- In each iteration, exactly one pointer moves, guaranteeing that the loop runs at most `N-1` times.
- ⇒ **O(N)**

**Space Complexity:**  
- The algorithm uses a fixed number of variables (`maxArea`, `leftPointer`, `rightPoint`, etc.).
- The memory required does not scale with the size of the input array.
- ⇒ **O(1)**

---

#### Layman’s Terms

- The algorithm starts by considering the widest possible container, using the first and last vertical lines.
- It calculates the area and then intelligently decides how to narrow down the search.
- The key insight is that the amount of water is always limited by the shorter of the two lines. If we move the pointer of the *taller* line inward, the container's width will shrink, but the height is still limited by that same shorter line. This means the area can *never* increase.
- Therefore, the only logical move is to discard the shorter line and move its pointer inward, hoping to find a taller line to create a larger area. This process repeats until the pointers meet.

👉 **Rule of thumb:** *If you double the number of lines in the input array, the time it takes to find the solution also roughly doubles. The memory usage remains constant and tiny.*

---

#### Conclusion

- **Efficiency:** The O(N) time and O(1) space solution is optimal. A brute-force O(N²) approach (checking every pair of lines) would be too slow for the problem's constraints.
- **Limits:** The solution is robust and has no significant limits within standard integer ranges.
- **Improvements:** The two-pointer approach is the standard, optimal solution for this problem. No further algorithmic improvements are necessary.
- **Lesson Learned:** This problem is a classic example of the **two-pointer technique**. It teaches how to transform a seemingly quadratic problem into a linear one by proving that large portions of the search space can be safely and greedily discarded. The core takeaway is the logical proof: moving the taller pointer is always a losing move, so we only ever need to move the shorter one.
