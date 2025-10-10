---
title: Find the Odd Integer
commit: 5d02db5
url: https://github.com/josimar-silva/kaizen/commit/5d02db5f15e451b47c71f8bab24436fbca4ae43a
---

#### Problem
Given an array of integers, find the one that appears an odd number of times.
Constraint: There will always be only one integer that appears an odd number of times.
---

#### Big O Analysis

**Time Complexity:**  
- The algorithm iterates through the input array once.  
- Each operation (XOR) is constant time.  
- ⇒ **O(N)**, where `N` is the number of elements in the input array.  

**Space Complexity:**  
- The algorithm uses a single variable to accumulate the XOR result.  
- The memory required is constant and does not scale with the size of the input array.  
- ⇒ **O(1)**

---

#### Layman’s Terms

- For small inputs: Very fast, a few XOR operations.  
- For medium inputs: Time taken grows linearly. If an array has 1,000 numbers, it performs roughly 1,000 XOR operations.  
- For large inputs: Remains highly efficient. Operations are proportional to the total number of numbers.  

👉 **Rule of thumb:** *If you double the length of the array, the time it takes to run the algorithm roughly doubles. The memory usage remains constant, like using a single counter regardless of the array's length.*

---

#### Conclusion

- **Efficiency:** The XOR solution is highly efficient and optimal, achieving O(N) time and O(1) space complexity. It's superior to the HashMap approach in terms of space.  
- **Limits:** The solution is robust and handles the specified constraints well. It relies on the guarantee that exactly one integer appears an odd number of times.  
- **Improvements:** The XOR approach is already highly optimized for this specific problem. No significant algorithmic improvements are possible.  
- **Lesson Learned:** This problem highlights the power of bitwise operations, specifically XOR, for efficiently solving problems involving unique or duplicate elements. The property `A ^ A = 0` and `A ^ 0 = A` allows for an elegant, order-independent solution that avoids explicit counting and additional data structures. It's a great example of how understanding fundamental mathematical properties can lead to highly optimized algorithms.

---