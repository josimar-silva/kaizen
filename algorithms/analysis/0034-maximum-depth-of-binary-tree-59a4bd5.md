---
title: Maximum Depth of a Binary Tree
commit: 59a4bd5
url: https://github.com/josimar-silva/kaizen/commit/59a4bd50d2a6f11c04e9a73a2532240c422b4a9d
---

#### Problem
Given the root of a binary tree, find its maximum depth. The depth is defined as the number of nodes along the longest path from the root node down to the farthest leaf node.

---

This problem can be solved using two primary traversal strategies: Depth-First Search (DFS) and Breadth-First Search (BFS). Both are optimal in terms of time complexity, but they have important differences in their implementation and space usage.

### Approach 1: Depth-First Search (DFS) via Recursion

This is the most direct and intuitive approach. The logic relies on the observation that the maximum depth of a tree is `1` (for the current node) plus the maximum depth of its left and right subtrees.

#### Big O Analysis

**Time Complexity:** `O(N)`
- The function visits every node in the tree exactly once.
- ⇒ **O(N)**

**Space Complexity:** `O(H)` where `H` is the height of the tree.
- Memory is consumed by the recursion stack. The maximum depth of the stack is equal to the maximum height of the tree.
- For a well-balanced tree, this is `O(log N)`. For a completely skewed tree (like a linked list), it degrades to `O(N)`.
- ⇒ **O(N)** (Worst Case)

---

### Approach 2: Breadth-First Search (BFS) via Iteration

This approach explores the tree level by level, using a queue to keep track of nodes to visit. It avoids recursion, which can be an advantage in environments with limited stack depth.

#### Big O Analysis

**Time Complexity:** `O(N)`
- Like the DFS approach, this method also visits every node exactly once.
- ⇒ **O(N)**

**Space Complexity:** `O(W)` where `W` is the maximum width of the tree.
- Memory is consumed by the queue, which at its peak holds all the nodes at the widest level of the tree.
- For a complete binary tree, the last level can contain up to `N/2` nodes. For a skewed tree, the width is always 1.
- ⇒ **O(N)** (Worst Case)

---

#### Layman’s Terms

Imagine you're exploring a maze.

- **DFS (Recursive):** You are one person. You run down one path as far as you can go. When you hit a dead end, you backtrack to the last junction and try a different path. Your memory usage is based on how many turns you've made (the depth of your path).

- **BFS (Iterative):** You are a commander with a team. You send your team to explore all rooms one step away. Once they're done, you send them to all rooms two steps away, and so on, level by level. Your resource usage is based on how many rooms there are at the widest level of the maze.

👉 **Rule of thumb:** For both methods, doubling the number of nodes doubles the processing time. The difference is in memory.

---

#### Conclusion

- **Efficiency:** Both DFS and BFS are optimally efficient in terms of time, solving the problem in a single pass.

- **Limits & Trade-offs:** The choice between DFS and BFS is a classic space-time trade-off, but in this case, it's about *which kind* of space you use.
    - **DFS (Stack Space):** Elegant and simple to code, but risks a `StackOverflowError` on very **deep** and narrow trees.
    - **BFS (Heap Space):** Slightly more complex to implement, but safer for deep trees. It can, however, consume significant memory for very **wide** and shallow trees.

- **Lesson Learned:** The structure of the input data is critical when choosing between algorithms with similar time complexities. For tree problems, the choice between DFS and BFS often comes down to the tree's expected shape. If you anticipate deep, chain-like structures, BFS is generally safer. If you expect wide, bushy structures, DFS might be more memory-efficient.
