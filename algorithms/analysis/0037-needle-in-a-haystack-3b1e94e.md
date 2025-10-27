---
title: Needle in a Haystack
commit: 3b1e94e
url: https://github.com/josimar-silva/kaizen/commit/3b1e94e74604fa3678fa67c4a8f5cec4177ca30c
---

#### Problem
Given two strings, `haystack` and `needle`, find the index of the first occurrence of `needle` within `haystack`. If `needle` is not found, return -1. A key edge case is that an empty `needle` is considered to be found at index 0 of any `haystack`.

---

#### Big O Analysis

**Time Complexity:**  
- The algorithm uses a `while` loop that iterates through the `haystack`. In the worst case, this loop runs approximately `N - M + 1` times, where `N` is the length of the `haystack` and `M` is the length of the `needle`.
- Inside each iteration of the loop, two primary operations occur:
    - `haystack.substring(leftPointer, rightPointer)`: This operation extracts a substring of length `M`. Depending on the language runtime, this can take `O(M)` time if it involves copying characters.
    - `possibleNeedle == needle`: The string comparison also takes `O(M)` time in the worst case, as it compares up to `M` characters.
- Therefore, the total time complexity is `(N - M + 1) * O(M)`, which simplifies to **O(N * M)**.

**Space Complexity:**  
- We use a few pointers (`leftPointer`, `rightPointer`) which take `O(1)` space.
- The `possibleNeedle` string created in each iteration has a maximum length of `M`. If `substring` creates a new string object, this contributes `O(M)` to the auxiliary space.
- ⇒ **O(M)**

---

#### Layman’s Terms

- **For small inputs:** Imagine searching for a short word ("cat") in a short sentence ("The cat sat on the mat."). The computer quickly checks each possible starting point, comparing "cat" to "The", then "he ", then "e c", and so on. It's almost instantaneous.
- **For medium inputs:** Now, search for a phrase ("quick brown fox") in a paragraph. The computer still checks every possible starting point, and for each, it compares the entire phrase. You might notice a slight delay if you perform this many times, as the comparisons add up.
- **For large inputs:** If you're trying to find a long sentence ("The quick brown fox jumps over the lazy dog.") within an entire book, the computer has to do a lot of work. For every single character in the book, it might have to compare the entire long sentence. It's like trying to find a specific paragraph by reading every single word in the book, one by one, from every possible starting point.
- 👉 **Rule of thumb:** If the book (haystack) doubles in size, or the sentence (needle) doubles in size, the time it takes could roughly double. If both double, the time could quadruple!

---

#### Conclusion

- **Efficiency:** This brute-force sliding window solution is straightforward to understand and implement. It is generally efficient enough for scenarios where the `haystack` and `needle` strings are of small to moderate lengths.
- **Limits:** It breaks down when `haystack` and `needle` become very long, as the `O(N*M)` complexity leads to significantly increased processing time. It's not suitable for high-throughput systems or searching in massive datasets.
- **Improvements:** More advanced algorithms like Knuth-Morris-Pratt (KMP) or Boyer-Moore can achieve better time complexities (e.g., O(N+M) for KMP) by pre-processing the `needle` to avoid redundant comparisons and make larger "jumps" in the `haystack`. This often involves a trade-off of increased space for pre-computed tables.
- **Lesson Learned:** Always consider the scale of your inputs when choosing an algorithm. A simple, intuitive solution might be perfectly fine for small data, but understanding Big O notation helps identify bottlenecks and when to reach for more optimized, albeit complex, algorithms. Edge cases (like empty strings) are crucial for robust API design.
