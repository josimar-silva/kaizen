---
title: Binary Search
commit: 84a63be
url: https://github.com/josimar-silva/kaizen/commit/84a63be5f9b01a362cf5ff34d4a9c19b86ed0a98
---

#### Problem
The challenge involves implementing a function to efficiently search for a `target` integer within a given array of integers, `numbersOrdered`. The array is guaranteed to be sorted in ascending order and contain unique elements. If the `target` is found, its index must be returned; otherwise, -1 should be returned. A critical constraint is that the algorithm must achieve an **O(log n) runtime complexity**.

---

#### Big O Analysis

**Time Complexity:**
Binary Search operates by repeatedly dividing the search interval in half. In each step, the algorithm eliminates half of the remaining elements.
- The search space `n` is halved in each iteration.
- This process continues until the search space is reduced to a single element or becomes empty.
- The number of operations required is proportional to the number of times `n` can be divided by 2 until it reaches 1. This is the definition of a logarithm base 2.
- ⇒ **O(log n)**

**Space Complexity:**
The algorithm uses a constant amount of extra space for variables like `left`, `right`, and `mid`, regardless of the input array size. It does not create any new data structures that grow with the input.
- No auxiliary data structures are allocated that scale with `n`.
- Variables used for tracking indices are fixed in number.
- ⇒ **O(1)**

---

#### Layman’s Terms

Imagine searching for a specific word in a dictionary. You don't start from the first page and read every word. Instead, you open roughly to the middle. If your word comes alphabetically before what you see, you discard the latter half; if it comes after, you discard the first half. You repeat this process, always halving the remaining section, until you find your word or realize it's not there.

- For small inputs (e.g., an array of 10 elements): A linear search might take up to 10 checks. Binary search would take at most log₂(10) ≈ 3-4 checks. It feels almost instantaneous.
- For medium inputs (e.g., an array of 1,000 elements): A linear search could take up to 1,000 checks. Binary search would only take about log₂(1,000) ≈ 10 checks. This is already a significant difference.
- For large inputs (e.g., an array of 1,000,000 elements): A linear search would be extremely slow, potentially 1,000,000 checks. Binary search, however, would still be incredibly fast, requiring only about log₂(1,000,000) ≈ 20 checks. This efficiency is critical for large-scale data.

👉 **Rule of thumb:** *What happens when input doubles?* If you double the size of the array, a linear search will take twice as long. A binary search will only take one additional step (e.g., log₂(2n) = log₂(n) + log₂(2) = log₂(n) + 1). This is why logarithmic algorithms are preferred for large datasets when applicable.

---

#### Conclusion

-   **Efficiency:** Binary search is exceptionally efficient for searching in sorted arrays, offering a significant performance advantage over linear search, especially as dataset sizes grow. Its `O(log n)` time complexity makes it suitable for real-world applications where quick lookups are paramount.
-   **Limits:** The primary limitation is the strict requirement for the input array to be sorted. If the data is unsorted, the cost of sorting it (e.g., `O(n log n)` for efficient sorts) must be factored in, potentially negating the benefits of binary search if many searches are not performed on the same sorted data. It also doesn't inherently handle duplicate elements or rotated sorted arrays without modifications.
-   **Improvements:** While the core binary search is robust, potential improvements or extensions could include:
    *   Adapting it to find the first or last occurrence of a duplicate element.
    *   Modifying it to search in a rotated sorted array.
    *   Considering iterative vs. recursive implementations based on context and potential stack depth issues for very large inputs in recursive versions.
-   **Lesson Learned:** The key takeaway is the immense power of ordered data. When data can be maintained in a sorted state, highly efficient algorithms like binary search become available, drastically reducing computation time. This principle extends beyond simple arrays to more complex data structures like B-trees, which optimize disk I/O operations in databases based on similar logarithmic search patterns. Understanding these fundamentals allows engineers to design systems that scale effectively.
