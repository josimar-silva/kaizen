## Algorithm: Valid Parentheses

**Source:** [Leet Code](https://leetcode.com/problems/valid-parentheses)  
**Commit:** [6c1646f](https://github.com/josimar-silva/kaizen/commit/6c1646fb3dfaac6cec1885410c79135c6d5147a9)

---

### Problem
Given a string `s` containing just the characters `(`, `)`, `{`, `}`, `[` and `]`, determine if the input string is valid. An input string is valid if open brackets are closed by the same type of brackets and in the correct order.

---

### Big O Analysis

**Time Complexity:**  
- The algorithm iterates through the input string `s` once from beginning to end.
- For each character, it performs a constant number of operations (a `when` check and a stack push or pop).
- ⇒ **O(n)**, where `n` is the length of the string.

**Space Complexity:**  
- The algorithm uses a stack to store the expected closing parentheses.
- In the worst-case scenario, the input string consists of only opening parentheses (e.g., `(([[{{`...), and the stack will grow to the size of the input string.
- ⇒ **O(n)**

---

### Layman’s Terms

- For any given string of parentheses, the algorithm looks at each character only once.
- It uses a stack (like a pile of plates) to keep track of the opening brackets it has seen.
- When it sees an opening bracket, it adds the corresponding closing bracket to the top of the pile.
- When it sees a closing bracket, it checks if it matches the one on top of the pile. If it does, it removes it. If it doesn't, or if the pile is empty, the string is invalid.

👉 **Rule of thumb:** *The time to check the string and the memory it uses grow in direct proportion to the length of the string.*

---

### Reflection

- **Efficiency:** This stack-based approach is the standard and most efficient way to solve this problem. It is optimal in terms of time and space complexity.
- **Limits:** The solution is limited by the maximum size of the stack, which in this case is tied to the length of the input string. Given the constraints (string length up to 10^4), this is not an issue.
- **Improvements:** The current implementation is clean and efficient. There are no significant algorithmic improvements to be made.
- **Lesson Learned:** This problem is a classic example of how a stack can be used to solve problems related to matching pairs or nested structures. The Last-In, First-Out (LIFO) nature of a stack is perfect for ensuring that brackets are closed in the correct order.

---
