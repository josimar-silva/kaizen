---
title: Valid Anagram
commit: d4082c4
url: https://github.com/josimar-silva/kaizen/commit/d4082c436e8bed6dc03a45233e9a50792fd0a6e2
---

#### Problem
The "Valid Anagram" problem requires determining if two given strings, `s` and `t`, are anagrams of each other. An anagram is formed by rearranging the letters of a different word or phrase, using all the original letters exactly once. The function should return `true` if `t` is an anagram of `s`, and `false` otherwise.

Constraints for this problem are:
- The lengths of both strings `s` and `t` are between 1 and 5 * 10<sup>4</sup> characters.
- Both strings consist solely of lowercase English letters.

This problem, while seemingly simple, touches on fundamental concepts of string manipulation and data structure choices for efficient comparison. The core idea is to verify if two strings have the exact same character composition, irrespective of character order.

---

#### Big O Analysis

**Time Complexity:**
The most efficient approaches involve iterating through both strings once to count character frequencies.
- Initial check for string length equality is O(1).
- Iterating through the first string (`s`) to populate character counts takes O(N) time, where N is the length of `s`.
- Iterating through the second string (`t`) to decrement character counts and check for validity takes O(M) time, where M is the length of `t`.
- ⇒ **O(N + M)**, or simply **O(L)** where L is the maximum length of the strings, as N and M are effectively equal if they are anagrams.

**Space Complexity:**
The space complexity depends on the data structure used to store character frequencies.
- Using an `IntArray` of fixed size (e.g., 128 for ASCII characters or 26 for lowercase English letters) to store character counts. This size is constant, regardless of the input string lengths.
- Using a `mutableMapOf` (hash map) in Kotlin would store at most 26 entries for lowercase English letters.
- In both presented solutions, the auxiliary space used does not grow with the input size N or M.
- ⇒ **O(1)**, as the space required is constant relative to the alphabet size, not the input string length.

---

#### Layman’s Terms

Think of an anagram as a recipe. If you have a recipe for "nagaram" and a recipe for "anagram," you'd check if they both require the exact same ingredients in the exact same quantities, even if the instructions (character order) are different.

- **How it works:** We "count the ingredients" (characters) in the first string. Then, we go through the second string and "use up" those ingredients. If we ever need an ingredient we don't have, or if we finish and still have ingredients left over, they aren't anagrams.

- **For short words (e.g., "cat" vs. "act"):** We'd quickly tally 'c':1, 'a':1, 't':1 for "cat". Then for "act", we'd use 'a', then 'c', then 't'. All counts would go to zero, so it's a match. It's almost instantaneous.

- **For medium sentences (e.g., a phrase of 50 characters):** Imagine doing this by hand. Counting would take a moment, and then checking each character in the second phrase would also take some time. The process is straightforward and quick.

- **For very long paragraphs (e.g., 50,000 characters):** Doing this manually would be tedious and error-prone. However, a computer can quickly scan both "paragraphs" to create and check the character counts. Even with 50,000 characters, it's a very fast operation because it only needs to pass through each string once. The number of unique characters to track (26 for English alphabet) remains tiny.

👉 **Rule of thumb:** *What happens when input doubles?* If you double the length of the strings, the time it takes to check them will roughly double. This "linear" increase in time is very efficient compared to approaches that might re-scan the strings multiple times or compare every character against every other character, which would become impractically slow for large inputs.

---

#### Conclusion

-   **Efficiency:** The character-counting approach (using an array or hash map) is highly efficient, achieving linear time complexity `O(N+M)` and constant space complexity `O(1)`. This makes it robust for strings up to the given length constraints.
-   **Limits:** The current solution is optimized for lowercase English letters due to the `IntArray(128)` and problem constraints. If the character set were extended (e.g., Unicode, case-sensitive, digits, special characters), the `IntArray` size would need adjustment or a more general `Map` would be necessary. The `O(1)` space complexity relies on a fixed, small alphabet size. If the alphabet size could be very large (e.g., full Unicode with millions of characters), then the space complexity would technically become `O(K)` where K is the alphabet size, or `O(min(N, K))` if only characters present in the string are counted, potentially approaching `O(N)` in the worst case for very diverse inputs.
-   **Improvements:** An alternative, less performant approach involves sorting both strings (`O(N log N)`) and then comparing them (`O(N)`), resulting in an overall `O(N log N)` time complexity. While simpler to conceptualize for some, it's asymptotically slower than the character-counting method. For this specific problem with its constraints, the character-counting method is optimal.
-   **Lesson Learned:** This problem elegantly demonstrates the power of choosing the right data structure (like a frequency array or hash map) to optimize an algorithm's time complexity. By transforming a comparison problem into a counting problem, we avoid nested loops or expensive sorting operations. It also highlights how understanding problem constraints (like character set and uniqueness) is crucial for selecting the most appropriate and efficient solution. The initial thought about Forward Error Correction, while abstract, underscored the intuitive drive to analyze and compare "information content" which is at the heart of this problem.
