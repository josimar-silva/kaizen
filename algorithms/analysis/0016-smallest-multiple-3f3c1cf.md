---
title: Smallest Multiple
commit: 3f3c1cff9c78a0fd5e59b6ffe29dc3672a08253b
url: https://github.com/josimar-silva/kaizen/commit/3f3c1cff9c78a0fd5e59b6ffe29dc3672a08253b
---

#### Problem
Find the smallest positive number that is evenly divisible by all the numbers from 1 to a given upper bound (e.g., 20). 
This is equivalent to finding the Least Common Multiple (LCM) of all integers from 1 up to that upper bound.

---

#### Big O Analysis

**Time Complexity:**
- The `smallestMultipleWithin` method iterates from `range.start()` to `range.end()`. Let `N = range.end()`. This loop runs `N` times.
- Inside the loop, `lowestCommonDivisor(result, i)` is called.
- The `lowestCommonDivisor` method calculates `(a * b) / greaterCommonDivisor(a, b)`.
- The `greaterCommonDivisor` method (Euclidean algorithm) has a time complexity of `O(log(min(a, b)))`.
- In each iteration of the `smallestMultipleWithin` loop, the `result` variable (which stores the LCM found so far) grows rapidly. The LCM of numbers from 1 to `k` can be very large.
- The value of `min(result, i)` in the `greaterCommonDivisor` call will be dominated by `i` in early iterations, but `result` quickly becomes the larger number. The numbers involved in the GCD calculation can grow up to the final LCM, which for `N=20` is `232,792,560`.
- The logarithm of the LCM of numbers up to `N` is approximately `N`. So, `log(min(a, b))` effectively becomes `O(N)` in the later iterations.
- Therefore, each call to `lowestCommonDivisor` can be considered `O(N)`.
- Since the main loop runs `N` times, the overall time complexity is `N * O(N) = O(N^2)`.

**Space Complexity:**
- The algorithm uses a fixed number of variables (`result`, `i`, `a`, `b`, `temp`) to store numeric values.
- The memory required for these variables is constant and does not scale with the input range.
- ⇒ **O(1)**

---

#### Layman’s Terms

- The algorithm starts with a `result` of 1.
- It then goes through each number in the given range (e.g., from 1 to 20).
- For each number, it calculates the "lowest common multiple" between the `result` so far and the current number.
- This calculation involves finding the "greatest common divisor" (GCD) using a very efficient method (the Euclidean algorithm).
- The numbers involved in these calculations grow very quickly.

👉 **Rule of thumb:** *If you double the upper limit of the range (e.g., from 10 to 20), the number of operations doesn't just double; it increases roughly by a factor of four. This is because the loop runs twice as many times, and each calculation inside the loop also takes roughly twice as long due to the larger numbers involved.*

---

#### Conclusion

- **Efficiency:** The solution is efficient for small ranges (like 1 to 20) due to the logarithmic nature of the GCD calculation. However, its `O(N^2)` time complexity means it would become impractical for very large ranges (e.g., 1 to 1000) as the numbers involved and the number of iterations grow.
- **Limits:** The primary limit is the rapid growth of the LCM itself, which can quickly exceed the capacity of `long` data types for larger ranges. The `O(N^2)` complexity also limits the practical upper bound of `N`.
- **Improvements:** A more optimized approach for finding the LCM of a range of numbers involves prime factorization. 
- **Lesson Learned:** This problem highlights the importance of understanding how intermediate values can grow and affect the complexity of an algorithm. While a direct iterative LCM calculation works, it's suboptiomal for larger numbers.