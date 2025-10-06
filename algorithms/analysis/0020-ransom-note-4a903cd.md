---
title: Ransom Note
commit: 4a903cd
url: https://github.com/josimar-silva/kaizen/commit/4a903cdf19867a4f8c61d166d471ba6527109e9c
---

#### Problem
Given two strings `ransomNote` and `magazine`, return `true` if `ransomNote` can be constructed by using the letters from `magazine` and `false` otherwise. Each letter in `magazine` can only be used once in `ransomNote`.

Constraints:
- `1 <= ransomNote.length, magazine.length <= 10^5`
- `ransomNote` and `magazine` consist of lowercase English letters.

---

#### Big O Analysis

**Time Complexity:**  
- The algorithm iterates through the `magazine` string once to count available characters.
- It then iterates through the `ransomNote` string once to check if characters can be constructed.
- Map/Array operations (get, put, increment, decrement) are, on average, O(1).
- ⇒ **O(N + M)**, where `N` is the length of `ransomNote` and `M` is the length of `magazine`.

**Space Complexity:**  
- The algorithm uses a data structure (either a `mutableMapOf<Char, Int>` or an `IntArray`) to store character counts.
- Since the problem constraints specify only lowercase English letters, the maximum number of unique characters is 26.
- ⇒ **O(1)**, as the space required is constant regardless of the input string lengths.

---

#### Layman’s Terms

- For small inputs (e.g., short `ransomNote` and `magazine`), the algorithm is almost instantaneous. It quickly counts characters in the `magazine` and then checks against the `ransomNote`.
- For medium inputs, the time taken grows linearly with the combined length of the two strings. If the `magazine` has 1,000 characters and `ransomNote` has 500, it performs roughly 1,500 operations.
- For large inputs (e.g., strings up to 10^5 characters), the algorithm remains highly efficient. It will perform operations proportional to the total number of characters, which is still very fast for modern computers.

👉 **Rule of thumb:** *If you double the combined length of the `ransomNote` and `magazine`, the time it takes to run the algorithm roughly doubles. The memory usage remains constant, like using a fixed-size notepad regardless of the book's length.*

---

#### Conclusion

- **Efficiency:** The `O(N + M)` time complexity and `O(1)` space complexity make this solution highly efficient and optimal for the given problem constraints. We established that O(N+M) is the theoretical lower bound as we must read all characters.
- **Limits:** The solution is robust and handles the specified constraints well. The use of `IntArray` or `mutableMapOf` for character counting is well-suited for the fixed, small character set.
- **Improvements:**
    - **Early Exit:** The addition of `if(magazine.length < ransomNote.length) return false` provides a quick exit for impossible cases, improving practical performance.
    - **Implementation Choice:** Two primary implementations were discussed:
        - **`mutableMapOf`**: Offers better readability and generalizability (if the character set were to expand beyond lowercase English letters).
        - **`IntArray`**: Provides marginal performance benefits due to direct indexing and lower object overhead, but involves "char math" (`char - 'a'`) which can be less intuitive for some developers.
    - Both achieve the same optimal Big O complexity.
- **Lesson Learned:** This problem highlights the importance of understanding the true lower bounds of an algorithm. While initial thoughts might lean towards "brute force," a careful analysis of data structures (like hash maps or fixed-size arrays for character counting) reveals an optimal linear time solution. It also demonstrates the trade-offs between code readability, maintainability, and micro-optimizations for specific constraints.

---