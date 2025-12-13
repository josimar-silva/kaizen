---
title: Diameter of Binary Tree
commit: 1ca6df4
url: https://github.com/josimar-silva/kaizen/commit/1ca6df4085773023d53ce980e20983e64c4a8a38
---

#### Problem
Given the root of a binary tree, return the length of the diameter of the tree. The diameter is defined as the length of the longest path between any two nodes in the tree. This path may or may not pass through the root, and the length is measured by the number of edges between nodes.

---

#### Big O Analysis

**Time Complexity:**
- The solution uses a depth-first search (DFS) traversal through a nested `heightOf` function.
- Each node in the tree is visited exactly once to calculate its height and update the maximum diameter.
- ⇒ **O(N)**, where N is the number of nodes in the tree.

**Space Complexity:**
- The space complexity is determined by the maximum depth of the recursion call stack for the `heightOf` function.
- In the worst-case scenario (a completely skewed tree resembling a linked list), the recursion depth can reach N.
- For a balanced tree, the recursion depth would be approximately log(N).
- ⇒ **O(N)** (Worst Case)

---

#### Layman's Terms

- **For small inputs (e.g., 5-10 nodes):** Computing the diameter feels instantaneous. It's like quickly measuring the width of a small family tree by hand—you can see all the relationships at a glance.

- **For medium inputs (e.g., 100-1,000 nodes):** The operation remains very efficient. Think of it as measuring the organizational structure of a medium-sized company: you visit each employee once, asking "how deep is your team?" while simultaneously noting the widest span you've encountered.

- **For large inputs (e.g., 10,000+ nodes):** The algorithm scales linearly, so it stays practical. However, with an extremely deep, chain-like tree (imagine a company where each manager has only one direct report for thousands of levels), you risk a `StackOverflowError` due to deep recursion. For balanced trees (more typical in practice), this isn't a concern.

👉 **Rule of thumb:** *What happens when input doubles?* The time required doubles, and the space used by the call stack also doubles in the worst case (skewed tree).

---

#### Conclusion

- **Efficiency:** The solution achieves optimal O(N) time complexity by visiting each node exactly once and computing both the height and diameter in a single traversal. This is the most efficient approach possible for this problem.

- **Limits:**
  - **Deep, Skewed Trees:** The recursive approach can encounter `StackOverflowError` for extremely deep trees due to the recursion stack depth.
  - **Tree Shape Dependency:** Space complexity varies based on tree shape—O(log N) for balanced trees, O(N) for skewed trees.

- **Improvements:** For production systems dealing with potentially very deep trees, an iterative approach using an explicit stack could be implemented to avoid recursion depth limits, though this would add implementation complexity.

- **Lesson Learned:**
  - **Bottom-Up Thinking:** The key insight is calculating the diameter *during* the height calculation rather than as separate operations. By computing height from the bottom up, we naturally encounter the information needed for the diameter (left height + right height) at each node.
  - **Mutable State in Closures:** Using a mutable `maxDiameter` variable captured by the nested `heightOf` function elegantly allows us to track the global maximum while performing local height calculations.
  - **Early Iterations Matter:** The journey through incomplete stack-based approaches and BFS with global state reinforced the elegance of the recursive DFS solution. Sometimes the simplest-looking solution (recursion) is indeed the best after exploring alternatives.
  - **Single Responsibility with Side Effects:** The `heightOf` function has dual purpose—it calculates height (return value) and tracks diameter (side effect). This pattern is powerful for tree problems where you need to propagate information both up (return values) and globally (closure variables).
