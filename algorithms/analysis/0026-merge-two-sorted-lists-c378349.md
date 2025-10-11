---
title: Merge Two Sorted Lists
commit: c378349
url: https://github.com/josimar-silva/kaizen/commit/c37834954bcea84d0f4613df8a685d74156f6c4c
---

#### Problem
You are given the heads of two sorted linked lists. Merge them into a single sorted linked list by splicing together the nodes of the original two lists.

---

#### Big O Analysis
This problem was solved using two primary approaches: recursive and iterative.

**1. Recursive Solution**
- **Time Complexity:** The function is called once for every node in the final merged list. If the lists have N and M nodes, this is N+M calls. ⇒ **O(N + M)**
- **Space Complexity:** Each recursive call adds a frame to the call stack. In the worst case (one list is empty), the recursion depth is the length of the non-empty list. ⇒ **O(N + M)**

**2. Iterative Solution**
- **Time Complexity:** A single `while` loop iterates as long as both lists have nodes. In each step, one node is processed. The total number of steps is proportional to the total number of nodes. ⇒ **O(N + M)**
- **Space Complexity:** Uses a few constant-size pointers (`dummyHead`, `tail`, `l1`, `l2`) regardless of the lists' sizes. ⇒ **O(1)**

---

#### Layman’s Terms
The goal is to weave two sorted linked lists into one, like merging two sorted lines of people.

- **Recursive way:** It's like saying, "I'll pick the first person, and then I'll delegate the task of merging the rest of the two lines to a helper." This is elegant, but it requires a chain of helpers (the call stack), which uses memory.

- **Iterative way:** This is like manually building the new, merged line. You have a pointer to the end of your new line (`tail`), and you repeatedly look at the front of the two source lines, append the correct person, and advance your `tail` pointer.

👉 **Rule of thumb:** *If you double the total number of nodes, both solutions take about twice as long. However, the recursive solution will use twice as much stack memory, while the iterative solution's memory usage remains constant and tiny.*

---

#### Conclusion

- **Efficiency:** Both solutions are optimal in time at O(N+M), since every node must be visited once. The iterative solution is superior in space complexity at O(1).
- **Limits:** The recursive solution's main limit is the maximum depth of the call stack, making it risky for very long lists (e.g., millions of nodes), where it could cause a `StackOverflowError`. The iterative solution is robust and has no practical limits.
- **Improvements:** The main improvement was moving from the recursive to the iterative solution to optimize space. No further algorithmic improvements are possible.
- **Lesson Learned:** This problem is a perfect case study for comparing **recursion vs. iteration**. Recursion often provides a more direct, elegant mapping to a problem's definition, but at the cost of space. Iteration, while requiring more manual state management (pointers), provides better performance and safety. The use of a **dummy head node** is a crucial pattern for simplifying iterative list-building algorithms.
