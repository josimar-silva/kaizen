---
title: Range Extraction
commit: 7bc9160
url: https://github.com/josimar-silva/kaizen/commit/7bc9160aea0a135c1dc53b0fed42ae7c39ef9174
---

#### Problem
Given a sorted list of integers, format it into a string where consecutive numbers (a sequence of 3 or more) are represented as a range (e.g., `1,2,3,4` becomes `1-4`). 
Sequences of less than 3 are listed as individual numbers.

---

#### Algorithmic Approach: Two Pointers

The problem was solved using a **Two Pointers** pattern, which is highly efficient for processing sequences within an array.

-   **`index` (Slow Pointer):** This pointer marks the beginning of a potential range. It only advances when a full sequence (be it a single number, a pair, or a range) has been processed.
-   **`next` (Fast Pointer):** This pointer scans ahead from the `index` to find the end of a consecutive sequence of numbers.

This pattern can be visualized like a pair of surveyors. The first (`index`) plants a stake at a starting point. The second (`next`) walks ahead as long as the path is straight (i.e., the numbers are consecutive). When the path curves (the sequence breaks), the second surveyor stops. They record the straight segment they found, and then the first surveyor *jumps* directly to the second surveyor's position to begin the next measurement. This "jumping" is key, as it prevents re-evaluating numbers that have already been scanned, ensuring linear time complexity.

The outer loop manages the `index` pointer, jumping it forward, while an inner loop advances the `next` pointer to find the end of a range.

---

#### Big O Analysis

**Time Complexity:** O(N)
-   Although the code uses nested `while` loops, each element in the input array is visited only a constant number of times. The outer loop's `index` pointer always jumps to the position where the inner loop's `next` pointer finished. This ensures that no element is processed more than once, leading to a linear runtime that scales directly with the size of the input array.
-   ⇒ **O(N)**

**Space Complexity:** O(N)
-   In the worst-case scenario (e.g., an array with no consecutive numbers like `[1, 3, 5, 7]`), the `result` vector must store a string representation for every single integer in the input. Therefore, the memory required grows linearly with the number of elements in the input array.
-   ⇒ **O(N)**

---

#### Conclusion

-   **Efficiency:** The O(N) time and O(N) space solution is optimal for this problem, as we must visit every element at least once and potentially store a representation of each one.

-   **Lesson Learned:** The main takeaway was the power of the **Two Pointers** pattern for partitioning an array. The key insight was realizing that the outer loop index must *jump* forward rather than incrementing by one. This required switching from a standard `for` loop to a `while` loop to gain manual control over the index, a crucial implementation detail in many languages, including Rust. This pattern is more robust and often simpler to reason about than managing state with flags.
