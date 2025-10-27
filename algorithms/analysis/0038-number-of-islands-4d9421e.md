---
title: Number of Islands (BFS and DFS)
commit: 4d9421e
url: https://github.com/josimar-silva/kaizen/commit/4d9421ea107c5f8bff95a079e6624c398633f7c7
---

#### Problem
The "Number of Islands" problem involves counting distinct landmasses in a 2D binary grid. The grid consists of '1's (representing land) and '0's (representing water). An island is defined as a group of connected '1's, where connectivity is established horizontally or vertically. The goal is to return the total count of such islands.

Example:
Given the grid:
```
1 1 0 0 0
1 1 0 0 0
0 0 1 0 0
0 0 0 1 1
```
The expected output is 3 islands.

---

#### Big O Analysis

**Time Complexity:**
- The algorithm iterates through every cell in the `N x M` grid using nested loops.
- When a land cell ('1') is found, a graph traversal (either BFS or DFS) is initiated to explore and "sink" the entire connected island by changing its '1's to '0's.
- Each cell in the grid is visited and processed by the traversal algorithm at most once.
- ⇒ **O(N*M)**, where N is the number of rows and M is the number of columns.

**Space Complexity:**
- **BFS (Breadth-First Search):**
  - Uses an explicit queue to store coordinates of cells to visit.
  - In the worst case (e.g., a grid full of land, or a very wide "wave" of exploration), the queue might hold elements proportional to the perimeter of the largest island or the shorter dimension of the grid.
  - ⇒ **O(min(N,M))** (a tighter bound, representing the maximum size of the BFS wavefront).
- **DFS (Depth-First Search):**
  - Uses the call stack (implicitly for recursive DFS) to manage exploration.
  - In the worst case (e.g., a grid entirely filled with land forming one giant island), the recursion depth can go as deep as the total number of cells.
  - ⇒ **O(N*M)** (representing the maximum depth of the recursion stack).

---

#### Layman’s Terms

Imagine you have a large map of land and water, and you want to count how many separate pieces of land there are.

-   **For small inputs (e.g., a 3x3 grid):** It's like quickly scanning a small map with your finger. You spot a piece of land, trace its boundaries, mark it off, and then look for the next one. It feels almost instantaneous.
-   **For medium inputs (e.g., a 50x50 grid):** You're still systematically scanning, but now you need a method to ensure you don't get lost or count the same landmass twice. When you find land, you use a systematic search (like BFS or DFS) to explore every connected part of that land, marking it as "visited" (or "sunk") as you go. This prevents you from revisiting it.
-   **For large inputs (e.g., a 300x300 grid):** The systematic approach becomes critical. Without it, you'd easily get overwhelmed. The BFS approach is like having a team of explorers who always fan out from the current shoreline, ensuring they cover all adjacent areas before moving deeper inland. They only need to remember the current "edge" of their exploration. The DFS approach is like a single explorer who dives deep into one path until they hit water, then backtracks to try another path. If the landmass is very large and winding, this explorer might need a very long rope (the call stack) to remember their way back, which can sometimes snap (stack overflow).

👉 **Rule of thumb:** *What happens when input doubles?* If you double both the rows and columns of your map, the amount of work you need to do to count all islands roughly quadruples, because you have four times as many cells to potentially examine.

---

#### Conclusion

-   **Efficiency:** Both BFS and DFS provide optimal time complexity for this problem, visiting each cell at most once. This makes them highly efficient for the given constraints.
-   **Limits:** While both are viable, recursive DFS can encounter stack overflow issues for extremely large, contiguous islands due to its deep recursion stack. BFS, using an iterative approach with an explicit queue, avoids this limitation, making it generally more robust for arbitrary graph structures or very large grids.
-   **Improvements:** For this specific problem, the BFS/DFS approach is a standard and highly optimized solution. Further significant algorithmic improvements are generally not sought beyond these graph traversal techniques.
-   **Lesson Learned:**
    -   **Graph Traversal Fundamentals:** This problem is a prime example of how BFS and DFS are used to find and process connected components in a grid (a specialized form of a graph).
    -   **In-place Modification for State Management:** Modifying the grid (changing '1's to '0's) is an effective way to mark visited cells and prevent re-processing, which is crucial for correctness and efficiency.
    -   **BFS vs. DFS Trade-offs:** Understanding the memory implications (stack depth for DFS vs. queue size for BFS) is vital for choosing the appropriate algorithm, especially when dealing with constraints that might lead to deep recursion.
    -   **Rigorous Testing:** When functions modify their input parameters in place, careful test setup (e.g., providing fresh copies of inputs for each assertion) is essential to avoid subtle bugs and ensure accurate verification.
