---
title: Middle of Linked List
commit: 347f133
url: https://github.com/josimar-silva/kaizen/commit/347f133ad86eff9387a864865fd67d937468a200
---

#### Problem
Given the head of a singly linked list, return the middle node of the list. If there are two middle nodes (even-length lists), return the second middle node. The challenge is to find this middle node efficiently without first counting the total number of nodes, which would require traversing the list twice.

Constraints for this problem are:
- The number of nodes in the list is in the range [1, 100].
- 1 <= Node.val <= 100

This problem introduces the classic two-pointer technique (also known as the "tortoise and hare" or "slow and fast" pointer approach), a fundamental pattern for linked list traversal that solves several categories of problems with optimal space efficiency.

---

#### Big O Analysis

**Time Complexity:**
The solution employs two pointers that traverse the list simultaneously at different speeds.
- The fast pointer advances two steps per iteration, while the slow pointer advances one step.
- When the fast pointer reaches the end of the list, the slow pointer will be at the middle.
- The loop executes approximately N/2 times for a list of N nodes (since the fast pointer moves twice as fast).
- Each iteration performs constant-time pointer advancement operations.
- ⇒ **O(N)**, where N is the number of nodes in the list.

**Space Complexity:**
The solution uses only two pointer variables regardless of input size.
- `slow` and `fast` are the only auxiliary variables used.
- No additional data structures (arrays, maps, or recursive call stacks) are required.
- ⇒ **O(1)** constant space.

---

#### Layman's Terms

Think of this as two runners on a circular track with different speeds:

- **How it works:** The slow runner (tortoise) takes one step while the fast runner (hare) takes two steps. When the fast runner finishes the race (reaches the end), the slow runner will be exactly at the halfway point. This eliminates the need to count how many steps the track has before finding the middle.

- **For small lists (e.g., 5 nodes: [1,2,3,4,5]):** The fast pointer visits nodes at positions 2, 4, null (off the end), while the slow pointer visits positions 1, 2, 3. When fast reaches null, slow is at node 3—the middle. This is nearly instantaneous.

- **For medium lists (e.g., 50 nodes):** The algorithm still makes only one pass through the list. The fast pointer "scouts ahead" while the slow pointer follows at half speed. By the time the fast pointer finishes (around 25 iterations), the slow pointer is positioned at the middle without needing to know the total count beforehand.

- **For large lists (e.g., 10,000 nodes):** The efficiency remains optimal. Instead of counting all 10,000 nodes first (one pass) and then traversing to the 5,000th node (second pass), the two-pointer technique finds the middle in a single pass. The fast pointer reaches the end in about 5,000 iterations, with the slow pointer perfectly positioned at the midpoint.

👉 **Rule of thumb:** *What happens when input doubles?* The time required doubles proportionally (linear growth), but space usage remains constant—just two pointers, whether the list has 10 nodes or 10,000.

---

#### Conclusion

- **Efficiency:** The two-pointer technique achieves optimal O(N) time complexity with O(1) space complexity, which is the best possible solution for this problem. Unlike the naive approach of counting nodes first (requiring two passes), this single-pass solution is both time-efficient and space-efficient. The algorithm elegantly demonstrates how multiple pointers moving at different speeds can extract information about a data structure's properties without auxiliary storage.

- **Limits:**
  - **Even-length list behavior:** The specific initialization `slow = head` and `fast = head.next` ensures that for even-length lists, the second middle node is returned (as required by the problem). If the first middle node were desired, both pointers would start at `head`.
  - **Singly-linked structure dependency:** This approach requires sequential traversal and cannot benefit from random access. For doubly-linked lists or array-backed lists where size is known, alternative approaches might be simpler.
  - **Edge case handling:** The implementation correctly handles empty lists and single-node lists through the early return `if (head == null || head.next == null) return head`.

- **Improvements:**
  - The current implementation is already optimal for this specific problem. No improvements in time or space complexity are possible while maintaining the single-pass requirement.
  - For variations (finding the first middle in even-length lists), simply changing the initialization to `fast = head` instead of `fast = head.next` would suffice.
  - This pattern extends to other linked list problems: detecting cycles (Floyd's cycle detection), finding the start of a cycle, removing the nth node from the end, and palindrome verification.

- **Lesson Learned:**
  - **The power of relative motion:** By moving pointers at different speeds, we can extract positional information (like "middle") without explicitly counting or measuring. When one pointer moves twice as fast, the relationship between their positions reveals structural properties of the data.
  - **Initialization matters:** The choice of starting `fast` at `head.next` rather than `head` directly controls which middle node is returned for even-length lists. This subtle initialization difference demonstrates how small implementation choices encode problem requirements.
  - **Space-time trade-offs:** While we could solve this in O(N) time and O(1) space by counting first (two passes), the two-pointer technique achieves the same complexity with a single pass—showing that sometimes clever pointer manipulation is more elegant than brute-force counting.
  - **Pattern recognition:** The tortoise-and-hare technique is a fundamental pattern that appears across numerous linked list problems. Mastering it provides a reusable mental model for entire categories of list traversal challenges.
  - **Edge case clarity:** The early return for null or single-node lists demonstrates defensive programming. Rather than relying on the loop to handle these cases, explicit handling makes the code's behavior immediately clear and prevents potential null pointer issues.
