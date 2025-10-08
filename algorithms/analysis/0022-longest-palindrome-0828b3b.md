---
title: Longest Palindrome
commit: 0828b3b
url: https://github.com/josimar-silva/kaizen/commit/0828b3b609f8109dda3ea09fc20e382fa31758c9
---

#### Problem
Given a string `s` which consists of lowercase or uppercase letters, return the length of the longest palindrome that can be built with those letters. Letters are case sensitive.

---

#### Big O Analysis

**Time Complexity:**  
- The algorithm iterates through the input string `s` once.
- Inside the loop, `HashSet.add()` and `HashSet.remove()` operations take, on average, O(1) time.
- Final calculations are O(1).
- ⇒ **O(N)**, where `N` is the length of the input string.

**Space Complexity:**  
- The `HashSet` stores unique characters. Its size is bounded by the fixed alphabet size (e.g., 52 for English letters).
- ⇒ **O(1)**

---

#### Layman’s Terms

- For small inputs: Very fast, few operations.
- For medium inputs: Time taken grows linearly. If a string has 1,000 characters, it performs roughly 1,000 operations.
- For large inputs: Remains highly efficient. Operations are proportional to the total number of characters.

👉 **Rule of thumb:** *If you double the length of the string, the time it takes to run the algorithm roughly doubles. The memory usage remains constant, like using a fixed-size notepad regardless of the string's length.*

---

#### Conclusion

- **Efficiency:** The O(N) time complexity is highly efficient and optimal for this problem, as every character must be inspected at least once.
- **Limits:** The solution is robust and handles the specified constraints well. The space usage is minimal and tied to the size of the character set, not the length of the string itself.
- **Improvements:** While the Big-O time complexity is optimal, alternative implementations using an `IntArray` for frequency counting might offer marginal practical performance gains due to avoiding hashing overhead.
- **Lesson Learned:** This problem highlights how a `HashSet` can be effectively used to count character pairs and identify unpaired characters in a single pass, leading to an optimal linear time solution with constant space complexity. It also demonstrates the importance of correctly analyzing space complexity, especially when data structures are bounded by a fixed alphabet size rather than the input size.

---