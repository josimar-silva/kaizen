---
title: Remove Duplicates from Sorted Array
commit: 1ed41720
url: https://github.com/josimar-silva/kaizen/commit/1ed4172047dd6c9b6ff686e15a0871c418f07d44
---

#### Problem
Given a sorted integer array, remove the duplicates in-place such that each unique element appears only once while maintaining the original relative order. The function should return the number of unique elements.

---

#### Big O Analysis

**Time Complexity:**
- The algorithm utilizes a single loop that iterates through each element of the array once. This is the "fast pointer".
- Inside the loop, only a constant number of operations (a comparison, a potential increment, and a potential assignment) are performed.
- ⇒ **O(N)**

**Space Complexity:**
- The modification is done in-place, meaning we do not allocate any new arrays or data structures that scale with the input size.
- A few variables are used for pointers, which occupy a constant amount of memory.
- ⇒ **O(1)**

---

#### Layman’s Terms

A good analogy is organizing a bookshelf. Imagine you have a shelf of books sorted by title, but with many duplicate copies. You want to create a section on the same shelf with only one copy of each book.

The two-pointer method works like this:
1.  One hand (the `slowPointer`) stays at the end of your "unique" section, which initially just holds the first book.
2.  Your other hand (the `fastPointer`) scans through all the books on the shelf.
3.  You only move a book with your fast hand if its title is different from the book your slow hand is pointing to. When you find a different one, you move your slow hand one spot over and place the new book there.

This way, you process the whole shelf in one pass, using only your two hands.

👉 **Rule of thumb:** *If the number of books on the shelf doubles, the time it takes to organize it also doubles. The process scales linearly.*

---

#### Conclusion

- **Efficiency:** This two-pointer approach is optimally efficient, achieving the best possible O(N) time and O(1) space complexity.
- **Limits:** The solution is robust and has no practical performance limits under typical constraints.
- **Improvements:** No further algorithmic improvements are possible, as any solution must inspect every element at least once.
- **Lesson Learned:** This problem is a classic example of the "fast and slow pointer" pattern for in-place array manipulation. The key is to use the slow pointer to maintain the boundary of the valid, "clean" section of the array. Action is only taken (advancing the slow pointer and writing) when the fast pointer discovers an element that belongs in the clean section. This pattern is fundamental for many array problems.

---
