## Algorithm: Count Sheep

**Source:** https://www.codewars.com/kata/54edbc7200b811e956000556
**Commit:** [53c3415](<commit-url>)

---

### Problem
Counts the number of sheep present in an array (where `true` means present).

---

### Big O Analysis

**Time Complexity:**  
- The `iter().filter().count()` operation iterates through the input slice exactly once.
- ⇒ **O(n)**, where n is the number of elements in the input slice.

**Space Complexity:**  
- The `filter()` method creates an iterator but does not allocate a new collection. The `count()` method only maintains a counter.
- ⇒ **O(1)**, constant extra space.

---

### Layman’s Terms

- For small inputs: If you have 10 sheep, the function checks each of the 10 spots once. It's very quick.
- For medium inputs: If you have 1,000 sheep, it checks each of the 1,000 spots once. Still very fast.
- For large inputs: If you have 1,000,000 sheep, it checks each of the 1,000,000 spots once. The time taken grows directly with the number of sheep.
👉 **Rule of thumb:** If the number of sheep doubles, the time it takes to count them also doubles.

---

### Reflection

- **Efficiency:** The current implementation is highly efficient, performing a single pass over the input data. This is optimal for this problem.
- **Limits:** The input `&[bool]` can theoretically be very large. However, the return type `u8` limits the maximum count to 255. If the number of `true` values exceeds 255, the result will be truncated due to the `as u8` cast.
- **Improvements:** To handle arrays with more than 255 `true` values, the return type could be changed to `usize` (or `u16`, `u32`, etc., depending on the expected maximum count). The current Rust idiomatic approach using iterators is already very clean and efficient.
- **Lesson Learned:** Rust's iterator chain (`iter().filter().count()`) provides a concise and efficient way to process collections, often leading to highly optimized code without explicit loops.
