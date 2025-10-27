---
title: Balanced Binary Tree
commit: f7f6cf0
url: https://github.com/josimar-silva/kaizen/commit/f7f6cf0060d769b28be5474a3040a98042500368
---

#### Problem
Determine if a given binary tree is "height-balanced." This means that for **every node** in the tree, the difference in height between its left and right subtrees is no more than one.

---

### Approach 1: Naive Top-Down Recursion

This first approach directly translates the problem definition into code. It confirms three conditions at every node:
1. Is the left subtree balanced?
2. Is the right subtree balanced?
3. Do the heights of the left and right subtrees differ by at most 1?

#### Big O Analysis

**Time Complexity:** `O(N log N)` to `O(N²)`
- The `isBalanced` function visits each of the `N` nodes.
- At each node, it calls a `height` function on its two children. The `height` function must traverse the entire subtree below it.
- This results in visiting nodes multiple times. For a balanced tree, the complexity is `O(N log N)`. For a skewed, unbalanced tree (like a linked list), it degrades to `O(N²)` as it repeatedly traverses the decreasingly smaller subtrees.
- ⇒ **O(N²)** (Worst Case)

**Space Complexity:** `O(N)`
- The memory usage is determined by the maximum depth of the recursion stack.
- In the worst case of a completely skewed tree, the recursion depth can be `N`.
- ⇒ **O(N)**

---

### Approach 2: Optimal Bottom-Up Recursion

The inefficiency in the naive approach comes from separating the balance check from the height calculation. The optimal solution combines them into a single, efficient traversal.

A helper function traverses the tree from the bottom up (post-order). At each node, it calculates the height of its subtrees and simultaneously checks for balance. If an imbalance is detected at any point, it immediately signals this by returning a special value (e.g., `-1`), allowing the entire process to stop early.

#### Big O Analysis

**Time Complexity:** `O(N)`
- This approach visits every node in the tree exactly once.
- ⇒ **O(N)**

**Space Complexity:** `O(N)`
- The space complexity is still determined by the maximum depth of the recursion stack.
- ⇒ **O(N)**

---

#### Layman’s Terms

Think of it as a CEO checking a project's status.

- **Naive Approach:** The CEO asks Manager A for a status update. To provide it, Manager A reads every single document from their department. Then, the CEO asks Manager B. Later, as part of a different check, the CEO asks Manager A to verify a sub-project, forcing Manager A to re-read all the same documents again. It’s slow and full of redundant work.

- **Optimal Approach:** A junior employee writes a report and gives it to their manager, along with the time it took. The manager collects reports from all their directs, sums up the time, and passes a single, consolidated report and total time to their director. This continues up the chain. The CEO gets the final, complete status in one go, with no re-reading.

👉 **Rule of thumb:** With the naive approach, doubling the number of nodes can quadruple the work in the worst case. With the optimal approach, doubling the nodes only doubles the work.

---

#### Conclusion

- **Efficiency:** The naive `O(N²)` solution is simple to understand but too slow for large trees and would not be acceptable in production code. The optimal `O(N)` solution is highly efficient.

- **Limits:** The main limitation for both recursive approaches is the system's recursion depth limit. An extremely deep tree (e.g., millions of nodes) could theoretically cause a stack overflow.

- **Improvements:** For most use cases, the optimal recursive solution is ideal. To handle extreme tree depths beyond the stack limit, one could implement an iterative version using an explicit stack, though this adds significant code complexity.

- **Lesson Learned:** The key takeaway is the power of a **bottom-up** approach in tree problems. Instead of doing work at a node and then passing the problem down (top-down), we let the children solve the problem for themselves and pass the results *up*. By designing a function that can return multiple pieces of information (in this case, either a height or an error signal), we can avoid redundant computations and achieve optimal efficiency.
