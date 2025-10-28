---
title: Sum Square Difference
commit: ec6b4dc
url: https://github.com/josimarsilva/kaizen/commit/ec6b4dceb87a700151dd22a55bb44731fdc26c09
---

#### Problem
The challenge is to find the difference between two distinct calculations involving the first 'n' natural numbers:
1.  The sum of the squares of these numbers (e.g., 1² + 2² + ... + n²).
2.  The square of the sum of these numbers (e.g., (1 + 2 + ... + n)²).

The problem specifically asks for this difference for the first one hundred natural numbers.

---

#### Big O Analysis

**Time Complexity:**

-   **Naive (Iterative) Approach:**
    -   Calculating the sum of squares involves a loop that iterates 'n' times, performing a multiplication and an addition in each step.
    -   Calculating the sum of numbers also involves a loop that iterates 'n' times, performing an addition in each step.
    -   The final squaring and subtraction are constant time operations.
    -   ⇒ **O(N)**: The time taken grows linearly with the input 'n'.

-   **Optimized (Formulaic) Approach:**
    -   The sum of the first 'n' natural numbers can be calculated using the formula: `n * (n + 1) / 2`. This is a constant number of arithmetic operations.
    -   The sum of the squares of the first 'n' natural numbers can be calculated using the formula: `n * (n + 1) * (2n + 1) / 6`. This is also a constant number of arithmetic operations.
    -   The final squaring and subtraction are constant time operations.
    -   ⇒ **O(1)**: The time taken is constant, regardless of the input 'n'.

**Space Complexity:**

-   **Both Approaches:**
    -   Both the naive and optimized solutions only require a few variables to store intermediate sums and the final result. The memory usage does not grow with the input 'n'.
    -   ⇒ **O(1)**: The space taken is constant.

---

#### Layman’s Terms

Imagine you're counting apples in a very long line.

-   **For small inputs (e.g., n=10):** Both methods are incredibly fast. The iterative method is like counting each apple one by one. The formulaic method is like having a magic calculator that instantly tells you the total. For 10 apples, you won't notice a difference.

-   **For medium inputs (e.g., n=1,000):** The iterative method (O(N)) starts to take a noticeable amount of time. Counting 1,000 apples one by one is slower than using the magic calculator. The formulaic method (O(1)) is still instantaneous.

-   **For large inputs (e.g., n=1,000,000,000):** The iterative method becomes impractical. Counting a billion apples one by one would take an extremely long time. Your program might even crash or time out. The formulaic method, however, still gives you the answer instantly, as it's just a few arithmetic operations.

👉 **Rule of thumb:** *What happens when input doubles?*
-   If you double the number of apples for the iterative method, it will take roughly twice as long.
-   If you double the number of apples for the formulaic method, it will still take the same, almost instantaneous, amount of time.

---

#### Conclusion

-   **Efficiency:** The O(1) formulaic approach is vastly superior in terms of efficiency and scalability compared to the O(N) iterative approach, especially for large input values of 'n'. It's the preferred solution for this type of problem.

-   **Limits:**
    -   The O(N) iterative approach's practical limit is determined by the maximum acceptable execution time and potential timeouts for very large 'n'.
    -   The O(1) formulaic approach's primary limit is the data type's capacity (e.g., `long` in Java) to hold the result without overflow for extremely large 'n'.

-   **Improvements:** The transition from an iterative O(N) solution to a formulaic O(1) solution represents the most significant improvement. Further improvements would focus on handling potential `long` overflow for astronomically large 'n', perhaps by using `BigInteger`.

-   **Lesson Learned:** This session reinforced several key computer science fundamentals:
    1.  **Problem Decomposition:** Breaking down a complex problem into smaller, manageable parts (sum of squares vs. square of sum).
    2.  **Algorithmic Efficiency:** Understanding and analyzing time and space complexity (O(N) vs. O(1)).
    3.  **Leveraging Mathematical Properties:** Recognizing when mathematical formulas can replace iterative processes to achieve significant performance gains. This is a powerful optimization technique.
    4.  **Edge Case Consideration:** Even for seemingly simple problems, thinking about edge cases (like `n=0`, negative `n`, or extremely large `n`) is crucial for building robust and production-ready code.
    5.  **Testing:** The importance of comprehensive testing, including comparing naive and optimized solutions, to ensure correctness.
