---
title: Reverse Linked List
commit: 6aa5e19
url: https://github.com/josimar-silva/kaizen/commit/6aa5e19e1b6f63cb07e6114c8b68deac21d2f548
---

### Reversing a Linked List

### 1. The Iterative Approach (In-Place)

This method walks through the list and reverses the direction of the `next` pointers at each node. It's highly efficient as it doesn't use extra memory proportional to the list's size.

#### Analogy: The Train Uncoupling

Imagine a train on a track. To reverse it, you can't just turn the whole train around. Instead, a worker walks from the engine to the caboose, uncoupling each car and re-coupling it to the one behind it.

-   `currentNode`: The car the worker is currently at.
-   `previousNode`: The car that has just been re-coupled, now part of the "reversed" section.
-   `nextNode`: The worker keeps an eye on the next car in the original sequence, so they know where to go next after re-coupling the current one.

This process continues until the last car is reached, which then becomes the new engine.

#### Big O Analysis

-   **Time Complexity: O(n)**
    We visit each node in the list exactly once to rewire its `next` pointer. If the list has `n` nodes, we perform `n` operations.

-   **Space Complexity: O(1)**
    The memory required is constant. We only need three pointers (`previousNode`, `currentNode`, `nextNode`) to keep track of our state, regardless of whether the list has 10 nodes or 10 million.

---

### 2. The Recursive Approach

This method uses the function call stack to reverse the list. It traverses to the end of the list and then, as the stack unwinds, it reverses the pointers.

#### Analogy: The Domino Chain Reaction

Think of the list as a line of dominoes.

1.  **The Walk:** You walk to the very last domino in the line without touching any of them. This is the recursive traversal.
2.  **The Base Case:** You arrive at the last domino. This is the new "head" of the reversed line.
3.  **The Chain Reaction:** You tip the last domino so it falls backward, hitting the one before it (`head.next.next = head`). As each recursive call returns, it does the same, causing a chain reaction backward until the original first domino is the new last one.

#### Big O Analysis

-   **Time Complexity: O(n)**
    Similar to the iterative approach, each node is visited once during the traversal to the end.

-   **Space Complexity: O(n)**
    This is the key difference. For each node we traverse, a new frame is added to the function call stack. For a list of `n` nodes, the stack depth will be `n`. This means memory usage grows linearly with the size of the list.

---

### Conclusion & Key Takeaways

| Approach   | Time   | Space  | Analogy                | When to Use                                                              |
| :--------- | :----- | :----- | :--------------------- | :----------------------------------------------------------------------- |
| **Iterative** | O(n)   | O(1)   | Train Uncoupling       | Preferred in most production scenarios, especially with large inputs.     |
| **Recursive** | O(n)   | O(n)   | Domino Chain Reaction  | Good for conceptual understanding, but risky for large inputs due to potential **stack overflow** errors. |

**Lesson Learned:** The choice between iteration and recursion often comes down to a trade-off between implementation simplicity and space efficiency.
