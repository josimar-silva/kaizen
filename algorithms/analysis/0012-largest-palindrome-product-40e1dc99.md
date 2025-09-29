## Algorithm: Largest Palindrome Product

**Source:** [Project Euler](https://projecteuler.net/problem=4)  
**Commit:** [40e1dc9](https://github.com/josimar-silva/kaizen/commit/40e1dc994b9c8ee310be7620529376c3048574b8)

---

### Problem
A palindromic number reads the same both ways. The largest palindrome made from the product of two 2-digit numbers is 9009 = 91 × 99. Find the largest palindrome made from the product of two 3-digit numbers.

---

### Big O Analysis

**Time Complexity:**  
- The algorithm uses two nested loops to check the product of all pairs of n-digit numbers.
- Let `N` be the number of n-digit numbers (e.g., for 3 digits, N is 900).
- The number of iterations is roughly `N * N`.
- Inside the loops, it performs a palindrome check, which takes `O(log(product))` time.
- ⇒ **O(N^2 * log(N^2))**, where `N` is the number of n-digit numbers.

**Space Complexity:**  
- The algorithm uses a few variables to store the bounds, the current product, and the largest palindrome found.
- The memory usage is constant and does not scale with the number of digits.
- ⇒ **O(1)**

---

### Layman’s Terms

- The algorithm multiplies every possible pair of 3-digit numbers (from 999x999 down to 100x100).
- For each product, it checks if the number reads the same forwards and backwards.
- It keeps track of the largest palindrome it finds.
- The optimization `if (product <= largestPalindrome) { break; }` helps to prune the search space and speed up the process.

👉 **Rule of thumb:** *The number of operations grows very quickly as the number of digits increases. Adding just one digit (from 3 to 4) would make the problem about 100 times harder.*

---

### Reflection

- **Efficiency:** The brute-force approach is simple to implement and works well for a small number of digits like 3. However, it does not scale well.
- **Limits:** For a larger number of digits (e.g., 5 or 6), this solution would become too slow to be practical.
- **Improvements:** A more optimized approach could be to generate palindromes and then check if they are the product of two n-digit numbers. This would involve a more complex implementation but would be more efficient for a larger number of digits.
- **Lesson Learned:** This problem shows that a brute-force solution can be a valid starting point, especially when the input size is small and fixed. The optimization of breaking out of the inner loop when the product is smaller than the current largest palindrome is a good example of how to prune the search space in a brute-force algorithm.

---
