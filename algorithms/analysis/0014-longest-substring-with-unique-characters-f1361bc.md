---
title: Longest Substring Without Repeating Characters
commit: f1361bc
url: https://github.com/josimar-silva/kaizen/commit/f1361bc3e08f9c6d3374046bee4fce423893eaec
---

#### Problem
Given a string `s`, find the length of the longest substring that does not contain any repeating characters.

---

#### Big O Analysis

**Time Complexity:**
- The algorithm iterates through the string a single time using a `rightPointer`.
- Inside the loop, operations like map lookups (`slidingWindow[char]`) and insertions (`slidingWindow[char] = rightPointer`) are, on average, O(1).
- This results in a linear time complexity relative to the length of the input string.
- ⇒ **O(n)**

**Space Complexity:**
- The algorithm uses a hash map (`slidingWindow`) to store the most recent index of each character encountered.
- In the worst-case scenario, the map will store as many characters as there are unique characters in the character set (`k`). For example, if the string consists of only ASCII characters, the space is constant, O(128). If the character set is unbounded, the space is O(min(n, k)), where `n` is the string length.
- ⇒ **O(k)**, where k is the number of unique characters in the character set.

---

#### Layman’s Terms

The algorithm uses a "sliding window" approach to efficiently find the longest substring without repeating characters. Imagine you're reading the string from left to right.

- You keep track of the starting point of your current substring (`leftPointer`) and the last place you saw each character in a hash map.
- As you iterate through the string with a `rightPointer`, you check if the current character is already in your map.
- If it is, and its last seen position is within your current substring, you have a repeat. You must then slide the start of your window (`leftPointer`) to the position right after that last occurrence.
- You continuously update the map with the current character's position.
- At each step, you calculate the length of the current valid substring (`rightPointer - leftPointer + 1`) and keep track of the maximum length found so far.

👉 **Rule of thumb:** *What happens when input doubles?* The number of operations roughly doubles. A string with 100,000 characters will take about twice as long as one with 50,000 characters.

---

#### Conclusion

- **Efficiency:** The O(n) time complexity is highly efficient and optimal for this problem, as every character must be inspected at least once. It's perfectly suitable for real-world use cases within the given constraints (up to 5 * 10^4 characters).
- **Limits:** The algorithm has no significant limits for the given constraints. The space usage is minimal and tied to the size of the character set, not the length of the string itself, which prevents memory issues with very long strings.
- **Improvements:** The current implementation is already optimal. For a very small and fixed character set (like lowercase English letters), a fixed-size array could be used instead of a hash map to store character indices, which might offer a marginal performance improvement by guaranteeing O(1) access time. However, the hash map is a more flexible and general solution.
- **Lesson Learned:** The sliding window technique is a powerful tool for solving substring and subarray problems. Using a hash map to store the last seen index of elements is a key pattern that allows the window to "jump" or "slide" efficiently, avoiding nested loops and achieving linear time complexity.

---
