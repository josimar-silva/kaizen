---
title: Longest Common Prefix v1
commit: ebda9ed
url: https://github.com/josimar-silva/kaizen/commit/ebda9ed449bd2813c9786ded4dfe82bb80939e7d
---

#### Problem
Write a function to find the longest common prefix string amongst an array of strings. If there is no common prefix, return an empty string `""`.

---

#### Big O Analysis

**Time Complexity:**  
- The algorithm uses a horizontal scanning approach.
- Let `S` be the sum of all characters in all strings.
- In the worst-case scenario, the algorithm might have to compare every character of every string.
- The outer loop runs `n-1` times (where `n` is the number of strings).
- The inner `while` loop and the `startsWith` method can take up to `m` operations (where `m` is the length of the longest string).
- ⇒ **O(S)**, where `S` is the total number of characters in all strings.

**Space Complexity:**  
- The algorithm uses a constant amount of extra space.
- The `longestCommonPrefix` variable stores a substring of one of the input strings, so it does not count as extra space.
- ⇒ **O(1)**

---

#### Layman’s Terms

- The algorithm takes the first string as the initial guess for the longest common prefix.
- It then compares this guess with every other string in the list.
- If a string doesn't start with the current guess, it shortens the guess by one character from the end and tries again.
- This process continues until it finds a prefix that is common to all strings, or the guess becomes empty.

👉 **Rule of thumb:** *The time it takes to run the algorithm is proportional to the total number of characters in all the strings combined.*

---

#### Conclusion

- **Efficiency:** The horizontal scanning approach is a simple and intuitive way to solve the problem. Its efficiency is reasonable for many cases.
- **Limits:** The performance can be slow in cases where the longest common prefix is very short, but the strings themselves are very long. For example, `["longstring...a", "longstring...b"]`.
- **Improvements:** Other approaches like vertical scanning (comparing characters column by column) or a divide and conquer approach could be more efficient in some cases, but they also have a time complexity of O(S). A Trie (prefix tree) could also be used to solve this problem, which would be very efficient if you needed to perform this operation multiple times on the same set of strings.
- **Lesson Learned:** This problem demonstrates a simple and effective string manipulation technique. It also highlights how different algorithmic approaches (horizontal scanning, vertical scanning, divide and conquer, Trie) can be used to solve the same problem, with different performance characteristics in different scenarios.

---
