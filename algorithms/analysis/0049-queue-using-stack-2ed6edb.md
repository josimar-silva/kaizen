---
title: Implement Queue Using Stacks
commit: 2ed6edb
url: https://github.com/josimar-silva/kaizen/commit/2ed6edbef8ce3c31848fe63f1a12cfafc3164c42
---

#### Problem
Implement a first-in-first-out (FIFO) queue using only two stacks. The queue must support standard operations: `push(x)` to add elements to the back, `pop()` to remove from the front, `peek()` to view the front element, and `empty()` to check if the queue is empty. The constraint is that only standard stack operations (push to top, peek/pop from top, size, and isEmpty) are permitted.

---

#### Big O Analysis

**Time Complexity:**
The implementation uses two stacks: a write stack for push operations and a read stack for pop/peek operations, with lazy element transfer via a `moveElements()` helper function.

- `push()`: Elements are directly pushed onto the write stack → **O(1)**
- `pop()`: While `moveElements()` can take O(N) when transferring all elements from write to read stack, each element is moved at most once from write to read stack across all operations. Using the accounting method: each element experiences at most two pushes (one to write, one to read), giving amortized O(1) per operation → **O(1) amortized**
- `peek()`: Same as `pop()`, requires `moveElements()` call when read stack is empty → **O(1) amortized**
- `empty()`: Checks both stacks for emptiness → **O(1)**

**Space Complexity:**
- Two stacks store all N elements in the queue.
- A `front` optimization variable adds constant O(1) space.
- ⇒ **O(N)**, where N is the number of elements in the queue.

---

#### Layman's Terms

Think of this as organizing documents between two trays:

- **How it works:** You have a "write tray" where new documents arrive (stack behavior: last in, first out). When someone asks for the oldest document, you flip the entire write tray upside down into a "read tray"—this reversal turns the stack order into queue order. Now you can serve documents from the read tray in the correct order until it's empty, then flip the write tray again.

- **For small queues (e.g., 5-10 elements):** Adding elements is instant—they're pushed directly onto the write stack. The first time you peek or pop, there's a one-time flip of all elements to the read stack. After that, operations are instantaneous until the read stack empties.

- **For medium queues (e.g., 100-1,000 elements):** If you push 100 elements and then pop them all, only the first pop requires flipping the stack (one-time cost of 100 moves). The remaining 99 pops are instant. The work is amortized across all operations.

- **For large queues (e.g., 10,000+ elements):** The efficiency pattern persists—occasional O(N) flips, but most operations are O(1). If you alternate between push and pop frequently, you'll trigger more flips. However, if you do batches (many pushes, then many pops), it's very efficient. The amortized cost remains constant.

👉 **Rule of thumb:** *What happens when input doubles?* Space usage doubles (you're storing twice as many elements). Time per operation stays roughly constant on average, though the occasional flip takes twice as long—but happens proportionally less frequently.

---

#### Conclusion

- **Efficiency:** This two-stack approach achieves amortized O(1) for all queue operations—matching the performance of a native queue implementation—while respecting the stack-only constraint. The solution elegantly demonstrates how combining two LIFO structures with strategic reversal can simulate FIFO behavior.

- **Limits:**
  - **Worst-case latency:** Individual `pop()` or `peek()` calls can take O(N) when `moveElements()` triggers. For real-time systems requiring guaranteed constant-time operations, this could be problematic.
  - **Memory overhead:** Requires space for two separate stacks, though total space is still O(N).
  - **Front variable limitation:** The `front` optimization avoids calling `moveElements()` for peek operations, but only when the read stack is empty. Once elements move to the read stack, we must rely on `read.peek()`.

- **Improvements:**
  - The current implementation includes a `front` variable optimization that avoids calling `moveElements()` for the first `peek()` when no pops have occurred—a clever constant-time shortcut.
  - For systems requiring strict O(1) worst-case guarantees, a different data structure (like a circular buffer with head/tail pointers) would be more appropriate.
  - Could add incremental rebalancing: move a fixed small number of elements per operation to spread the cost more evenly.

- **Lesson Learned:**
  - **Amortized analysis and lazy evaluation:** While individual operations can be expensive, the total cost divided by the number of operations is what counts. The `moveElements()` function only transfers elements when necessary (when read stack is empty), ensuring each element moves at most once from write to read stack.
  - **Dual-purpose data structures:** Two simple stacks, each doing what they do best (LIFO), can simulate a completely different behavior (FIFO) through strategic coordination.
  - **Optimization with trade-offs:** The `front` variable is a micro-optimization that adds slight complexity but avoids unnecessary element movement for the common case of peeking before any pops.
  - **Stack operations as building blocks:** This problem reinforces that complex data structures can be built from simpler primitives—a fundamental concept in data structure design.
