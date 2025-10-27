---
title: Invert Binary Tree
commit: 1e3ad1a
url: https://github.com/josimar-silva/kaizen/commit/1e3ad1a417ab4e5e4af6bce397a15a99a6ec3ec7
---

#### Problem
Given the root of a binary tree, invert the tree, and return its root. This means swapping the left and right children of every node in the tree.

---

#### Big O Analysis

**Time Complexity:**  
- Both Depth-First Search (DFS) and Breadth-First Search (BFS) approaches require visiting every node in the tree exactly once to perform the swap operation.
- ⇒ **O(N)**, where N is the number of nodes in the tree.

**Space Complexity:**  
- **DFS (Recursive):** The space complexity is determined by the maximum depth of the recursion call stack. In the worst-case scenario (a skewed tree, resembling a linked list), the recursion depth can be N.
- **BFS (Iterative with Queue):** The space complexity is determined by the maximum number of nodes stored in the queue at any given time. In the worst-case scenario (a complete binary tree where the last level is full), the queue can hold approximately N/2 nodes.
- ⇒ **O(N)** for both approaches in their respective worst-case scenarios.

---

#### Layman’s Terms

- **For small inputs (e.g., 3-7 nodes):** Inverting a small tree feels instantaneous. It's like quickly flipping a few cards on a table; the effort is negligible.
- **For medium inputs (e.g., 50-100 nodes):** The operation is still very fast, but you can conceptualize the computer systematically processing each node. It's akin to organizing a small stack of papers, one by one, where the time taken is directly proportional to the number of papers.
- **For large inputs (e.g., 10,000+ nodes):** While the algorithm remains efficient (linear time), the total number of operations becomes substantial. For a very deep tree using recursive DFS, there's a risk of a `StackOverflowError` if the recursion depth exceeds system limits. For BFS, a very wide tree would consume more memory for the queue. Imagine sorting a large library: you know it will take time proportional to the number of books, and you'll need sufficient temporary space to manage them.

👉 **Rule of thumb:** *What happens when input doubles?* The time and space required will also roughly double.

---

#### Conclusion

-   **Efficiency:** Both the recursive DFS and iterative BFS solutions achieve optimal O(N) time complexity, as every node must be visited to perform the inversion.
-   **Limits:**
    *   **DFS (Recursive):** Prone to `StackOverflowError` for extremely deep trees due to excessive recursion depth.
    *   **BFS (Iterative):** More robust against stack overflow issues, but its memory usage for the queue can be significant for very wide trees.
-   **Improvements:** For scenarios requiring robustness against deep trees, an iterative approach (either BFS or an iterative DFS using an explicit stack) is preferable. The choice between BFS and DFS often depends on the problem's specific requirements (e.g., shortest path problems typically favor BFS, while deep exploration might favor DFS).
-   **Lesson Learned:**
    *   **Tree Traversal Fundamentals:** Solidified understanding of Depth-First Search (DFS) and Breadth-First Search (BFS) strategies, including their recursive and iterative implementations.
    *   **Complexity Analysis:** Reinforced the application of O(N) time and space complexity analysis to tree traversal algorithms.
    *   **Algorithmic Trade-offs:** Highlighted the practical trade-offs between recursive elegance (DFS) and iterative robustness (BFS) in terms of potential stack overflow risks and memory management.
    *   **Systematic Debugging:** Emphasized the importance of a systematic approach to debugging and the critical role of a correctly configured test harness.
