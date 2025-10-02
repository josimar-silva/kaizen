---
title: Palindrome Number
commit: bd8281b
url: https://github.com/josimar-silva/kaizen/commit/bd8281b0811e1f3c3deb3c184b698cf1df77ef45
---

#### Problem
Given an integer `x`, return `true` if `x` is a palindrome, and `false` otherwise. An integer is a palindrome when it reads the same backward as forward.

---

#### Big O Analysis

**Time Complexity:**  
- The algorithm iteratively reverses the second half of the number.
- The number of iterations in the `while` loop is proportional to the number of digits in the input integer `x`.
- For an integer with `d` digits, the loop runs approximately `d/2` times.
- The number of digits `d` can be calculated as `log10(x)`.
- ⇒ **O(log10(n))**

**Space Complexity:**  
- The algorithm uses a few variables (`y`, `reversedNumber`, `lastDigit`) to store integer values.
- The memory required for these variables is constant and does not scale with the size of the input integer `x`.
- ⇒ **O(1)**

---

#### Layman’s Terms

- For small inputs, like `121`, the algorithm is almost instantaneous. It performs only a couple of operations.
- For medium inputs, like `123454321`, the number of operations is still very small, around 4-5 iterations. The growth is very slow.
- For large inputs, like a number with 18 digits (the maximum for a 64-bit integer), it would take around 9 iterations. The algorithm is extremely efficient even for the largest possible inputs.

👉 **Rule of thumb:** *The number of operations grows as the number of digits in the input, not the value of the input itself. Doubling the number of digits will roughly double the operations.*

---

#### Conclusion

- **Efficiency:** This solution is highly efficient and perfectly suitable for real-world use. The logarithmic time complexity and constant space complexity make it very performant.
- **Limits:** The algorithm is limited by the constraints of the integer type (`-2^31 <= x <= 2^31 - 1`). It correctly handles these limits.
- **Improvements:** The current solution is already very optimized. A possible alternative would be to convert the number to a string and compare the reversed string, but this would likely be slower and use more space (O(log10(n)) space for the string).
- **Lesson Learned:** This problem is a good reminder that mathematical manipulation of numbers can often be more efficient than converting them to other data types like strings. The trick of reversing only half the number is a clever optimization.

---
