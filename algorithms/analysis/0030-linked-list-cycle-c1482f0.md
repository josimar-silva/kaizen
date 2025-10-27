---
title: Linked List Cycle
commit: c1482f0
url: https://github.com/josimar-silva/kaizen/commit/c1482f08ac88d31b184c9d532c47eb4d87f0b432
---

#### Problem
The challenge is to determine if a given linked list contains a cycle. A cycle exists if a node in the list can be reached again by continuously following the `next` pointer. 

---

### Approach 1: Visited Nodes Set (Hash Set)

This is an intuitive approach where we traverse the list while storing each visited node in a hash set. Before visiting a node, we check if it's already in our set.

#### Big O Analysis

**Time Complexity:**
- We visit each node in the linked list at most once.
- For each node, the hash set insertion and lookup operations take, on average, constant time, O(1).
- ⇒ **O(n)**

**Space Complexity:**
- In the worst-case scenario (a list with no cycle), the hash set will store every node.
- The memory required grows linearly with the number of nodes in the list.
- ⇒ **O(n)**

---

### Approach 2: Two Pointers (Floyd's Tortoise and Hare)

A more optimized solution that uses two pointers moving at different speeds. This approach avoids the need for extra storage.

- **Slow Pointer (The Tortoise):** Moves one step at a time.
- **Fast Pointer (The Hare):** Moves two steps at a time.

If the list contains a cycle, the fast pointer will eventually enter the loop and lap the slow pointer. If there is no cycle, the fast pointer will reach the end of the list.

#### Big O Analysis

**Time Complexity:**
- The algorithm makes a single pass through the list. The number of steps is proportional to the number of nodes.
- ⇒ **O(n)**

**Space Complexity:**
- This method only requires two pointers (`slow` and `fast`).
- The memory usage is constant and does not depend on the size of the linked list.
- ⇒ **O(1)**

---

#### Layman’s Terms

The core difference between the two approaches is memory.

Imagine a list with 10 million nodes.
- The **Hash Set** approach would require storing 10 million node references, which could consume a significant amount of memory (e.g., 80 MB if each reference is 8 bytes).
- The **Two-Pointer** approach would only use two node references, a trivial amount of memory, regardless of the list's size.

👉 **Analogy (The Racetrack):** Think of the linked list as a racetrack.
- If the track is a straight line, the faster runner (Hare) will simply finish first.
- If the track is a loop, the Hare will eventually lap the slower runner (Tortoise). Their meeting proves the track is circular.

---

#### Conclusion

- **Efficiency:** While both solutions have the same linear time complexity, the Two-Pointer method is vastly more space-efficient, making it the superior choice for large-scale or memory-constrained environments.
- **Limits:** The Hash Set approach is limited by available system memory. It would be impractical for very large lists.
- **Lesson Learned:** When dealing with pointer-based data structures, clever pointer manipulation can often eliminate the need for auxiliary storage. The "fast and slow pointer" is a fundamental pattern for detecting cycles, finding middle points, and other related list problems. It's a powerful technique to have in your toolkit.

---
