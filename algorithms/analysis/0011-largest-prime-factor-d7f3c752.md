## Algorithm: Largest Prime Factor

**Source:** [Project Euler](https://projecteuler.net/problem=3)  
**Commit:** [d7f3c75](https://github.com/josimar-silva/kaizen/commit/d7f3c7529dc25f7834074b45eab6dce469f4612e)

---

### Problem
The prime factors of 13195 are 5, 7, 13 and 29. What is the largest prime factor of the number 600851475143?

---

### Big O Analysis

**Time Complexity:**  
- The algorithm iterates through potential factors (`divider`) in a `while` loop.
- The loop continues as long as `divider * divider` is less than the number being factored (`toFactor`). This means the loop runs up to the square root of the original number.
- ⇒ **O(sqrt(n))**, where `n` is the input number.

**Space Complexity:**  
- The algorithm uses a few variables (`toFactor`, `divider`, `largestKnowFactor`) to store numeric values.
- The memory required for these variables is constant and does not scale with the size of the input number.
- ⇒ **O(1)**

---

### Layman’s Terms

- The algorithm tries to find the smallest prime numbers that divide the input number.
- It starts with 2 and checks if it divides the number. If it does, it keeps dividing by 2 until it can't anymore.
- Then it moves to 3, and so on, for all numbers up to the square root of the input number.
- This is efficient because if a number has a large prime factor, it must also have a small one.

👉 **Rule of thumb:** *The number of operations grows with the square root of the input number. This is much slower than logarithmic growth, but much faster than linear growth.*

---

### Reflection

- **Efficiency:** The O(sqrt(n)) time complexity is generally considered efficient for this type of problem, especially for numbers within the range of standard integer types.
- **Limits:** The solution works well for the given Project Euler problem. For extremely large numbers (e.g., those used in cryptography), more advanced factorization algorithms like the Pollard's rho algorithm or the quadratic sieve would be necessary.
- **Improvements:** The current implementation is a good, standard approach. A small optimization could be to handle the factor 2 separately and then only check odd numbers as potential dividers.
- **Lesson Learned:** This problem is a great introduction to prime factorization algorithms. The key takeaway is the optimization of only checking for factors up to the square root of the number, which dramatically reduces the number of required computations.

---
