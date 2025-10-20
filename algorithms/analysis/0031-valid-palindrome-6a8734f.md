---
title: Valid Palindrome
commit: 6a8734f
url: https://github.com/josimarsilva/kaizen/commit/6a8734f3701211baec0a54355a1ee4f4a0e01bb4
---

#### Problem
Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring case. An empty string is considered a valid palindrome.

---

### Approach 1: String Manipulation (Naive)

This straightforward approach first "sanitizes" the input string by converting it to lowercase and removing all non-alphanumeric characters. It then compares the resulting string with its reversed version.

#### Big O Analysis

**Time Complexity:**
- The sanitization process (lowercase, replace) and the reversal each require iterating through the string. These are sequential O(N) operations.
- ⇒ **O(N)**

**Space Complexity:**
- This method creates several new strings in memory: one for the lowercase version, one for the version with non-alphanumeric characters removed, and a third for the reversed string.
- While one might describe this as O(3N), in Big O notation, we drop constant factors. The key insight is that memory usage grows linearly with the input size `N`.
- ⇒ **O(N)**

---

### Approach 2: Two Pointers (In-Place)

An optimal approach that avoids creating new strings, thus using constant extra space. It uses two pointers, one at the beginning (`left`) and one at the end (`right`) of the string, and moves them inwards.

1. The pointers move toward each other.
2. If a pointer lands on a non-alphanumeric character, it is skipped.
3. Once both pointers are on valid characters, they are compared (case-insensitively).
4. If they don't match, it's not a palindrome. If they do, the pointers are moved, and the process continues.

#### Big O Analysis

**Time Complexity:**
- Each pointer traverses each character at most once.
- ⇒ **O(N)**

**Space Complexity:**
- This method only requires a few variables to store the pointer indices.
- The memory usage is constant and does not depend on the size of the input string.
- ⇒ **O(1)**

---

#### Layman’s Terms

The primary difference is memory efficiency.

- The **String Manipulation** approach is like writing a cleaned-up version of a sentence on a new piece of paper, then writing it again backwards on another, and finally comparing the two papers. This uses a lot of paper.
- The **Two-Pointer** approach is like reading the sentence with one finger from the start and another from the end, moving your fingers inward and comparing letters as you go, simply skipping over spaces and punctuation. This uses no extra paper.

👉 **Rule of thumb:** For a string with 1 million characters, the naive approach might allocate memory for 3 million extra characters, while the two-pointer approach allocates memory for just two integers (the pointers).

---

#### Conclusion

- **Efficiency:** Both solutions are fast enough for typical inputs, but the Two-Pointer method is significantly more memory-efficient. In high-throughput or memory-constrained systems, avoiding the allocations of the naive method is a major advantage.
- **Limits:** The naive approach's memory usage makes it less suitable for extremely large strings.
- **Lesson Learned:** The Two-Pointer technique is a fundamental pattern for "in-place" processing of sequential data. It allows for efficient solutions that avoid the overhead of creating intermediate data structures, which is a critical consideration in performance-sensitive code.

---
