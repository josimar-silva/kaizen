---
title: 10001st Prime
commit: 35d7880
url: https://github.com/josimar-silva/kaizen/commit/35d7880ff9bcf19fe9b80cb070b26aa4991805a1
---

#### Problem
The challenge is to find the prime number at a given position `N` (e.g., the 6th prime is 13, the 10001st prime is 104743).

---

#### Big O Analysis

**Time Complexity:**

-   **`calculate()` method:** This method iterates through numbers, calling `isPrime()` for each, until it has found the `N`-th prime number. Let `P_N` be the `N`-th prime number.
    -   The `while` loop runs approximately `P_N` times (since it checks every number up to `P_N`).
    -   For each iteration, it calls `isPrime(number)`.

-   **`isPrime(number)` method:** This function checks for divisibility up to `sqrt(number)`.
    -   In the worst case (when `number` is close to `P_N`), `isPrime()` takes `O(sqrt(P_N))` time.

-   **Overall `calculate()` complexity:** To find the `N`-th prime, the total time is the number of `isPrime` calls multiplied by the cost of the most expensive `isPrime` call.
    -   This results in `O(P_N * sqrt(P_N))`, which simplifies to `O(P_N^(3/2))`.
    -   Using the Prime Number Theorem, `P_N` is approximately `N * ln(N)`. Substituting this into our expression:
    -   ⇒ **O((N * ln(N))^(3/2))**

**Space Complexity:**

-   **Both methods:** The algorithms only use a fixed number of variables regardless of the input `N` (e.g., `primeCount`, `lastKnownPrime`, `number`).
-   ⇒ **O(1)**

---

#### Layman’s Terms

Imagine you're searching for a special type of pearl (a prime number) by checking every oyster (number) in a very long line. You need to find the `N`-th such pearl.

-   **For small inputs (e.g., N=6):** Both checking if an oyster has a pearl (`isPrime`) and finding the `N`-th pearl (`calculate`) are very fast. You quickly check a few oysters, and you find your 6th special pearl.

-   **For medium inputs (e.g., N=1,000):** You're checking more oysters (`P_N` grows), and checking each individual oyster might take a bit longer (as `sqrt(P_N)` grows). This means the overall process starts to slow down noticeably.

-   **For large inputs (e.g., N=10,001):** As you go further down the line, prime numbers become sparser, meaning you have to check many more oysters to find the next special pearl. Also, the cost of checking each oyster grows. Both factors combine to make the process significantly slower – it grows much faster than simply doubling when N doubles.

👉 **Rule of thumb:** *What happens when input doubles?*
-   If `N` (the position of the prime you're looking for) doubles, the time taken will increase by a factor much larger than 2, because not only do you have to search further, but each primality test takes longer.

---

#### Conclusion

-   **Efficiency:** While correct, this Trial Division approach to finding the `N`-th prime is not the most efficient for very large `N`. Its performance degrades significantly as `N` increases.

-   **Limits:** This algorithm becomes computationally expensive for extremely large `N` (e.g., finding the billionth prime). The time taken would be prohibitive due to the `P_N^(3/2)` growth.

-   **Improvements:** For finding large `N`-th primes, more advanced algorithms like pre-computing primes using a Sieve of Eratosthenes up to an estimated upper bound (e.g., `N * ln(N) + N * ln(ln(N))`) and then finding the `N`-th prime within that list, or using more sophisticated prime-counting functions and primality tests, would be necessary.

-   **Lesson Learned:** This session reinforced:
    1.  **Algorithmic Optimization:** The importance of optimizing sub-problems (like `isPrime`) and understanding how their complexity impacts the overall algorithm.
    2.  **Growth of Functions:** The distinction between linear, polynomial, and combined complexities, and how to thoroughly analyze them.
    3.  **Real-world Performance:** Understanding that even a robust solution might not be performant enough for all scales, necessitating the exploration of more advanced algorithms.
    4.  **Mathematical Foundations:** How number theory (e.g., Prime Number Theorem, properties of square roots) is crucial for accurate complexity analysis.
