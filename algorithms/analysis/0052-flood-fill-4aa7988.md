---
title: Flood Fill
commit: 4aa7988
url: https://github.com/josimar-silva/kaizen/commit/4aa7988a3095b4710d258b4955ce3d07cfbab51b
---

#### Problem
Given a 2D matrix representing an image, a starting coordinate `(sr, sc)`, and a `newColor`, change the color of all connected pixels that share the same original color as the starting pixel. This process is recursive, spreading to all 4-directionally adjacent pixels of the same color.

This is fundamentally a graph traversal problem on an implicit graph where each pixel is a node and adjacent pixels of the same color share an edge.

---

### Initial Approach & The Hidden Bug

A Depth-First Search (DFS) is a natural fit for this problem. The initial implementation attempted to use DFS but contained a subtle, hard-coded assumption. The code checked for a specific "empty" pixel value (e.g., `0`) to stop the recursion, which led to a bug.

**The Bug:** The algorithm failed when the *starting color to be replaced* was the same as the hard-coded "empty" value.

**Analogy:** Imagine your task is to repaint all connected `blue` walls in a house to `red`. Your instruction sheet says, "Don't paint any wall that is a primary color." This faulty rule would stop you from painting the `blue` walls, as blue is a primary color. The instruction should have been, "Only paint walls that are the *exact same shade of blue* as the first wall you started with."

The initial code was making a similar faulty assumption, leading it to terminate prematurely.

### The Solution: The "Original Color" Invariant

The robust solution is to treat the problem as a classic "find and replace" operation. To do this, the algorithm must know two things:
1.  The color to find (`originalColor`).
2.  The color to replace it with (`newColor`).

The fix involves capturing the color of the starting pixel, `image[sr][sc]`, and passing it down through every recursive call. The core condition of the recursion then becomes: "If the current pixel's color is not the `originalColor`, stop."

This makes the algorithm general and robust, as it no longer relies on assumptions about pixel values.

---

### Design Decision: Boundary Checking

During the implementation, a design choice emerged for handling out-of-bounds coordinates in the grid:

1.  **Check Inside Callee:** Make the recursive call, and have the function's first action be to check if its own coordinates are valid.
    -   **Pros:** Centralizes the boundary logic at the top of the function.
    -   **Cons:** Results in extra function calls on the stack that immediately return, adding minor overhead.
2.  **Check Before Calling:** Before making a recursive call, check if the target coordinates are valid.
    -   **Pros:** Prevents unnecessary function calls, potentially improving performance slightly.
    -   **Cons:** Distributes the boundary logic among the four recursive call sites.

This is a common trade-off. The second approach is often slightly more performant, while the first can sometimes be seen as cleaner. The final implementation uses the second approach.

---

#### Big O Analysis

**Time Complexity:**
- The algorithm visits each pixel in the connected component at most once. In the worst case, it traverses the entire grid.
- ⇒ **O(N)**, where `N` is the total number of pixels in the image (`m * n`).

**Space Complexity:**
- The space is determined by the maximum depth of the recursion, which creates frames on the call stack.
- In the worst-case scenario (e.g., a single-pixel-wide snake that covers the entire grid), the recursion depth can be proportional to the total number of pixels.
- ⇒ **O(N)**.

---

#### Conclusion

- **Efficiency:** For the given constraints, a standard DFS or BFS approach is perfectly efficient and is the optimal way to solve this problem.

- **Limits:** The primary limitation is the recursion depth. For an extremely large grid, a recursive DFS could theoretically lead to a `StackOverflowError`. In such a scenario, an iterative solution using an explicit stack would be a more robust alternative.

- **Lesson Learned:** The key takeaway is the importance of identifying the correct **invariant** for a recursive algorithm. A brittle assumption (e.g., `0` is always an ignorable value) creates bugs. Basing the core logic on a dynamic condition captured at the start (the `originalColor`) ensures the algorithm is robust and handles all edge cases correctly.
