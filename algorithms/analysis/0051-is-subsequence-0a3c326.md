---
title: Is Subsequence
commit: 0a3c326
url: https://github.com/josimar-silva/kaizen/commit/0a3c3269f63dc72f455e1df664b5ef2078a3291c
---

#### Problem
Given two strings, `s` and `t`, determine if `s` is a subsequence of `t`. A subsequence is formed from the original string by deleting some characters without disturbing the relative order of the remaining characters.

Example: `s = "ace"` is a subsequence of `t = "abcde"`, but `s = "aec"` is not.

This problem has two parts:
1.  A single check of `s` against `t`.
2.  A follow-up scenario where we check billions of `s` strings against the same `t`.

---

### Approach 1: Two Pointers (for a single check)

This approach uses two pointers, one for `s` (`s_ptr`) and one for `t` (`t_ptr`), to perform a single linear scan.

- Initialize both pointers to `0`.
- Iterate through `t` with `t_ptr`.
- If `s[s_ptr]` matches `t[t_ptr]`, increment `s_ptr` to look for the next character in `s`.
- Always increment `t_ptr` to continue scanning `t`.
- The process stops when `t_ptr` reaches the end of `t`.
- `s` is a subsequence if, and only if, `s_ptr` has reached the end of `s`.

#### Big O Analysis

**Time Complexity:**
- We perform a single pass through the target string `t`.
- ⇒ **O(T)**, where `T` is the length of `t`.

**Space Complexity:**
- We only use a few variables (pointers) to store state.
- ⇒ **O(1)** (constant space).

---

### Approach 2: Pre-computation + Binary Search (for many checks)

When faced with a massive number of queries (`K` strings) against the same target `t`, the first approach becomes inefficient at `O(K * T)`. The bottleneck is re-scanning `t` repeatedly. This second approach invests in an upfront pre-computation to make each subsequent query much faster.

1.  **Pre-computation (Indexing):** First, we build a dictionary (a `Map<Char, List<Int>>`) that maps each character in `t` to a sorted list of all indices where it appears. This is like creating an index at the back of a book; it takes time to build, but once done, lookups are fast.
2.  **Querying:** For each character in `s`, we use binary search on the corresponding list in our dictionary to find the *next available index* that is strictly greater than the index of the previously found character. This is analogous to using the book's index: to find the next word in a phrase, you don't re-read the whole book, you just find the next valid page number.

#### Big O Analysis

**Time Complexity:**
- **Setup:** Building the dictionary requires a single pass through `t`. ⇒ `O(T)`.
- **Query:** For each `s` (average length `S`), we perform `S` binary searches. A binary search on an index list of max length `L` is `O(log L)`.
- **Total:** ⇒ **O(T + K * S * log L)**, where `T` is `target.length`, `K` is the number of `s` strings, `S` is the average length of an `s` string, and `L` is the max occurrences of any character in `t`.

**Space Complexity:**
- Storing the dictionary requires space proportional to the number of characters in `t`.
- ⇒ **O(T)**.

---

#### Conclusion

- **Efficiency:** The Two-Pointer approach is highly efficient for single-shot checks. For the high-volume follow-up scenario, the Pre-computation + Binary Search approach is vastly superior, as `log L` is significantly smaller than `T`. This is a classic **time-space trade-off**: we use more memory (`O(T)`) to achieve a much faster query time.

- **Limits:** The second approach's memory usage could be a concern if the target string `t` is astronomically large (e.g., hundreds of gigabytes), but for the constraints given, it's the ideal solution.

- **Lesson Learned:** Identifying the repetitive work in a naive algorithm is the key to optimization. For high-volume query systems, pre-processing the static data is a powerful and common design pattern. The `binarySearch` return value is not just a success/failure flag; its negative form contains valuable information about the "insertion point," which is critical for finding the next available ordered element.
