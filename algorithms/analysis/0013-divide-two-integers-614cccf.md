## Algorithm: Divide Two Integers

**Source:** [Leet Code](https://leetcode.com/problems/divide-two-integers/)  
**Commit:** [614cccf5](https://github.com/josimar-silva/kaizen/commit/614cccf589da7170f56256b59e62f766f960dbf2) 
**Language:** Kotlin 

---

### Problem
Given two integers `dividend` and `divisor`, divide two integers without using multiplication, division, and mod operator. The integer division should truncate toward zero. Return the quotient after dividing dividend by divisor. Handle edge cases like `Int.MIN_VALUE / -1` which should return `Int.MAX_VALUE`.

---

### Big O Analysis

**Time Complexity:**  
- The algorithm uses an outer `while` loop that reduces the `currentDividend`.
- Inside, an inner `while` loop efficiently finds the largest power-of-two multiple of the `divisor` that can be subtracted. This inner loop performs a logarithmic number of operations (up to 32 for 32-bit integers).
- The outer loop also runs a logarithmic number of times because in each iteration, a significant portion of the `currentDividend` is subtracted.
- ⇒ **O(log(N))**, where `N` is the magnitude of the dividend (or the quotient).

**Space Complexity:**  
- The algorithm uses a few variables (`quotientSign`, `currentDividend`, `flippedDivisor`, `quotient`, `tempDivisor`, `multiple`).
- The memory required for these variables is constant and does not scale with the size of the input integers.
- ⇒ **O(1)**

---

### Layman’s Terms

- For small inputs, like `10 / 3`, the algorithm is very fast, performing only a few subtractions.
- For medium inputs, like `1000 / 7`, it still performs very few operations. Instead of subtracting 7 one by one, it quickly subtracts 7*2, then 7*4, then 7*8, and so on, until it finds the largest chunk to subtract.
- For large inputs, like `Int.MAX_VALUE / 1`, the algorithm is extremely efficient. Instead of `2.1 billion` subtractions, it performs roughly `log₂(2.1 billion)` operations, which is about 31-32 steps.

👉 **Rule of thumb:** *The number of operations grows very slowly, logarithmically, with the magnitude of the dividend. Doubling the magnitude of the dividend only adds a single extra step.*

---

### Reflection

- **Efficiency:** This optimized solution is highly efficient, with logarithmic time complexity and constant space complexity. It's the standard optimal approach for this problem given the constraints.
- **Limits:** The solution correctly handles the 32-bit signed integer range and the specific overflow case of `Int.MIN_VALUE / -1`.
- **Improvements:** The current implementation is already very optimized. Further improvements would likely involve hardware-specific instructions or assembly, which are beyond the scope of a general algorithm.
- **Lesson Learned:** This problem is a fantastic demonstration of how understanding bit manipulation and the properties of numbers can lead to significant algorithmic optimizations. It transforms a potentially linear-time solution into a highly efficient logarithmic-time solution, showcasing the power of reducing the problem space exponentially.

---