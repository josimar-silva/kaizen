---
title: Sum of Multiples of 3 or 5
commit: 7ded72a
url: https://github.com/josimar-silva/kaizen/commit/7ded72ad7cd016340b7042671bbae9ff1442fe2c
---

#### Problem
If we list all natural numbers below 10 that are multiples of 3 or 5, we get:  
`3, 5, 6, 9` → their sum is `23`.  

The goal is to **find the sum of all multiples of 3 or 5 below 1000** (or a given `max`).  

---

#### Big O Analysis

**Time Complexity (Technical):**  
- Loop iterates through all numbers from `1` to `max`.  
- Each iteration does: modulo check (`%`) + conditional.  
- ⇒ **O(n)** time, where `n = max`.  

**Space Complexity:**  
- Uses only an integer accumulator (`sum`).  
- ⇒ **O(1)** space.  

---

#### Layman’s Terms

- The algorithm **checks every number one by one**.  
- Each check is very fast (just division remainder).  
- If the number is a multiple of 3 or 5, it adds it to the sum.  

👉 Scaling intuition:  
- For `max = 10` → checks 9 numbers (instant).  
- For `max = 1,000` → checks 999 numbers (tiny cost).  
- For `max = 1,000,000` → checks nearly a million numbers (still fine).  

This grows **linearly**: if you double the limit, it takes about **twice as long**.  

---

#### Conclusion

- This brute-force solution is **perfectly fine for small ranges** like Euler’s 1000.  
- For very large ranges (e.g., `10^12`), iterating over all numbers would be too slow.  

💡 **Optimization:**  
There’s a mathematical shortcut using the **arithmetic series formula**:  
\[
\text{Sum of multiples of k below max} = k \times \frac{p(p+1)}{2}
\]  
where `p = (max-1) / k`.  

By computing directly:  
- Sum of multiples of 3  
- Sum of multiples of 5  
- Subtract sum of multiples of 15 (to avoid double-counting)  

You can solve the problem in **O(1) constant time**, no matter how large `max` is.  

💡 **Lesson Learned:** Simple brute force is fine for small inputs, but mathematical insights can reduce an algorithm from “linear” to **instant**.

---
