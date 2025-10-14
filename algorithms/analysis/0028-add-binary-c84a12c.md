---
title: Add Binary
commit: c84a12c
url: https://github.com/josimar-silva/kaizen/commit/c84a12c2c4b7e8dd2591cb6272717000ea16436a
---

#### Problem
Given two binary strings, `a` and `b`, representing two non-negative integers, return their sum as a binary string. The input strings can be very long (up to 10,000 characters), precluding the use of standard integer types for conversion.

---

#### Algorithmic Approach: Manual Simulation

The chosen approach simulates the way humans perform addition by hand, column by column, from right to left. This is a fundamental pattern for handling arithmetic on numbers that exceed the capacity of standard data types.

The core of the algorithm is a `while` loop that continues as long as there are digits in either string to process or a final `carry` value remains. At each step, the algorithm calculates the sum of the two corresponding digits plus the carry from the previous step. The resulting bit for the current position is `sum % 2`, and the new carry for the next position is `sum / 2`.

This process is analogous to the grade-school method of decimal addition, but simplified, as the only possible values for digits are 0 and 1.

---

#### Big O Analysis

**Time Complexity:** O(max(N, M))
-   The algorithm iterates a number of times equal to the length of the longer string (`max(N, M)`), plus one potential extra iteration for a final carry. We visit each digit from both strings once. This is equivalent to `O(N + M)`. 
-   ⇒ **O(max(N, M))**

**Space Complexity:** O(max(N, M))
-   The `StringBuilder` used to construct the result will grow to a size proportional to the length of the longer input string. Therefore, the space required is linear with respect to the maximum input length.
-   ⇒ **O(max(N, M))**

---

#### Conclusion

-   **Efficiency:** The final solution is optimal, with linear time and space complexity.

-   **Lesson Learned:** The key takeaway was the importance of analyzing constraints first. The `10^4` length immediately ruled out naive conversion approaches and dictated a manual, digit-by-digit simulation. The session also highlighted the process of moving from a merely correct solution to a performant one by identifying and replacing an inefficient pattern (string concatenation in a loop) with a more robust alternative (`StringBuilder`).
