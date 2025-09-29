## Algorithm: Add Two Numbers

**Source:** [Leet Code](https://leetcode.com/problems/add-two-numbers/)  
**Commit:** [b988ccf](https://github.com/josimar-silva/kaizen/commit/b988ccf91ada66aab51fb7aa93fbd9957b677aa9)

---

### Problem
You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order, and each of their nodes contains a single digit. Add the two numbers and return the sum as a linked list.

---

### Big O Analysis

**Time Complexity:**  
- The algorithm iterates through the input linked lists `l1` and `l2` once.
- The number of iterations is determined by the length of the longer linked list.
- Let `m` and `n` be the lengths of `l1` and `l2` respectively. The `while` loop runs `max(m, n)` times.
- ⇒ **O(max(m, n))**

**Space Complexity:**  
- The algorithm creates a new linked list to store the sum.
- The length of the new linked list is at most `max(m, n) + 1` (to accommodate a final carry).
- ⇒ **O(max(m, n))**

---

### Layman’s Terms

- For small inputs, like two linked lists with 3 nodes each, the algorithm performs about 3 operations.
- For medium inputs, like two lists with 50 nodes each, it performs about 50 operations.
- For large inputs, with 100 nodes each (the maximum specified in the constraints), it performs about 100 operations. The time and space required grow in direct proportion to the length of the longest input list.

👉 **Rule of thumb:** *If you double the length of the input linked lists, you double the time it takes to compute the sum and the memory used to store the result.*

---

### Reflection

- **Efficiency:** This solution is optimal in terms of time and space complexity. Since we need to visit each node in the input lists at least once to know the numbers, we cannot do better than linear time. Similarly, we must create a new linked list for the result.
- **Limits:** The solution correctly handles lists of different lengths and the final carry-over.
- **Improvements:** The code is clean and efficient. There are no significant improvements to be made to the algorithm itself.
- **Lesson Learned:** This problem is a great exercise in manipulating linked lists and handling edge cases like carry-overs and lists of different lengths. The use of a `dummyHead` is a common and useful pattern for building new linked lists, as it simplifies the code by avoiding special handling for the head node.

---
