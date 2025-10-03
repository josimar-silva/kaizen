---
title: Swap Nodes in Pairs
commit: 2f3ce8c
url: https://github.com/josimar-silva/kaizen/commit/2f3ce8c
---

#### Problem
Given a singly linked list, swap every two adjacent nodes and return the modified list's head. The values within the nodes cannot be changed; only the nodes themselves (i.e., their pointers) can be modified.

---

#### Big O Analysis
This problem was solved using two primary approaches: recursive and iterative.

**1. Recursive Solution**
- **Time Complexity:** The function processes each node once. ⇒ **O(N)**
- **Space Complexity:** Each pair-swap results in a new call on the recursion stack. For a list of N nodes, there will be N/2 recursive calls. ⇒ **O(N)**

**2. Iterative Solution**
- **Time Complexity:** A single `while` loop iterates through the list, processing two nodes at a time. ⇒ **O(N)**
- **Space Complexity:** Uses a few constant pointers (`dummy`, `prev`, `n1`, `n2`) regardless of list size. ⇒ **O(1)**

---

#### Layman’s Terms
The goal is to rewire a linked list so that nodes switch places in pairs (1st and 2nd swap, 3rd and 4th swap, etc.).

- **Recursive way:** It's like saying, "I'll swap the first two nodes, and then I'll hand off the rest of the list to a clone of myself to solve the exact same problem." This is clean but uses more memory for each hand-off.
- **Iterative way:** This is like manually walking down the list with a toolkit. You need to keep track of where you came from (`prev`), the two nodes you're currently swapping (`n1`, `n2`), and then carefully reconnect everything before taking two steps forward to the next pair.

👉 **Rule of thumb:** *If you double the length of the list, both solutions take about twice as long. However, the recursive solution will use twice as much stack memory, while the iterative solution's memory usage remains constant.*

---

#### Conclusion

- **Efficiency:** Both solutions are optimal in time at O(N). The iterative solution is superior in space complexity (O(1)) and is generally preferred in a production environment to avoid potential stack overflow errors with very long lists.
- **Limits:** The recursive solution's main limit is the maximum depth of the call stack, making it risky for lists with millions of nodes. The iterative solution is robust and has no practical limits besides the total available memory for the list itself.
- **Improvements:** The presented solutions are standard and optimal. No significant algorithmic improvements are possible.
- **Lesson Learned:** This problem is a perfect case study for comparing recursion and iteration. Recursion offers elegance and maps well to the problem's self-similar nature. Iteration, while requiring more complex manual pointer management, provides better performance and safety against stack overflows. The use of a **dummy head node** in the iterative solution is a crucial pattern for simplifying edge cases when modifying a linked list's head.
