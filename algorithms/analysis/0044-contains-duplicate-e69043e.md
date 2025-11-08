---
title: Contains Duplicate
commit: e69043
url: https://github.com/josimar-silva/kaizen/commit/e69043ecd2cc4fd8c17612d74ab5f8309925d775
---

#### Problem
Given an array of integers, determine if any value appears at least twice. The function should return `true` if a duplicate exists, and `false` if all elements are distinct.

---

### Algorithmic Approaches & Analysis

This problem presents a classic space-time trade-off, offering two primary solutions with different performance characteristics.

---

### Approach 1: Hash Set (Time-Optimized)

This approach prioritizes execution speed by using an auxiliary data structure to keep track of the elements seen so far.

#### Big O Analysis

**Time Complexity:** O(N)
- We iterate through the array of N elements exactly once.
- For each element, we perform a `contains` check and an `add` operation on a hash set. On average, these are O(1) constant-time operations.
- ⇒ **O(N)**

**Space Complexity:** O(N)
- In the worst-case scenario (an array with no duplicates), the hash set will store all N elements.
- The memory required grows linearly with the size of the input array.
- ⇒ **O(N)**

---

### Approach 2: Sorting (Space-Optimized)

This approach prioritizes memory efficiency. By sorting the array, any duplicates are forced to be adjacent, making them easy to find.

#### Big O Analysis

**Time Complexity:** O(N log N)
- The dominant operation is sorting the array, which has an average time complexity of O(N log N) for efficient algorithms like Timsort or Introsort.
- After sorting, we perform a single pass (an O(N) operation) to check for adjacent duplicates.
- The overall complexity is determined by the sorting step.
- ⇒ **O(N log N)**

**Space Complexity:** O(1)
- If the array is sorted in-place, the additional space required is either constant, O(1), or logarithmic, O(log N), for the sort's recursion stack.
- This is significantly more memory-efficient than the hash set approach.
- ⇒ **O(1)**

---

#### Layman’s Terms

Imagine you're a bouncer at a large event, and your job is to prevent anyone from entering twice.

- **Hash Set Method (Fast with a Guest List):** You have a digital guest list (the hash set). When a guest arrives, you type their name in. The system instantly tells you if the name is already on the list. This is extremely fast, but you need the computer system to store the list of every guest who has entered.

- **Sorting Method (Slower with no Tech):** You have no computer. You let all the guests into a waiting room and ask them to form a single line, ordered alphabetically by name. Now, to find duplicates, you just walk down the line and see if any two people standing next to each other have the same name. The initial organization takes a while, but you didn't need any extra equipment.

👉 **Rule of thumb:** The Hash Set method is faster because you trade memory for instant lookups. The Sorting method is more memory-efficient because you spend extra time upfront to organize the data, which makes finding duplicates trivial.

---

#### Conclusion

- **Efficiency:** The "best" solution depends entirely on the operational context. For most typical application scenarios, the O(N) time complexity of the Hash Set method is preferred if the memory cost is acceptable.

- **The Engineering Decision:** This problem is a perfect illustration of a **space-time trade-off**.
    - Choose the **Hash Set** method when speed is the primary concern and memory is not a significant constraint.
    - Choose the **Sorting** method when memory is highly constrained (e.g., in embedded systems or processing massive datasets) and a slightly longer execution time is permissible.

- **Lesson Learned:** There is rarely a single "best" algorithm. The optimal choice is the one that best fits the constraints of the system. Recognizing and analyzing space-time trade-offs is a fundamental skill in software engineering.
