---
title: Majority Element
commit: d89efdd
url: https://github.com/josimar-silva/kaizen/commit/d89efdd70a9d28363ad0b7a7bf08ec5b07df9270
---

#### Problem
Given an array of size `n`, find the majority element. This is defined as the element that appears more than `⌊n / 2⌋` times. The problem guarantees that such an element always exists.

---

This problem presents a classic trade-off between a straightforward solution using extra memory and a more clever, space-efficient algorithm.

### Approach 1: Hash Map (Frequency Counter)

This intuitive approach involves counting the occurrences of each element in the array using a hash map and then finding the element with the highest count.

#### Big O Analysis

**Time Complexity:** `O(N)`
- A single pass `O(N)` is required to iterate through the array and populate the hash map.
- A second pass `O(k)` is required to find the maximum value in the map, where `k` is the number of unique elements (`k <= N`).
- ⇒ **O(N)**

**Space Complexity:** `O(k)` where `k` is the number of unique elements.
- In the worst case, the hash map would need to store counts for many unique elements.
- ⇒ **O(N)** (Worst Case)

---

### Approach 2: Boyer-Moore Voting Algorithm

This is a highly efficient and clever algorithm that solves the problem in a single pass with constant extra space. It works by maintaining a `candidate` element and a `counter`. The counter increments when we see the candidate and decrements otherwise. If the counter reaches zero, we pick a new candidate.

#### Big O Analysis

**Time Complexity:** `O(N)`
- The algorithm iterates through the array exactly once.
- ⇒ **O(N)**

**Space Complexity:** `O(1)`
- The algorithm only requires two variables: one for the `candidate` and one for the `count`, regardless of the input size.
- ⇒ **O(1)**

---

#### Layman’s Terms

Imagine an election.

- **Hash Map Approach:** You are a vote counter with a large whiteboard. For every vote you read, you add a tally mark next to the candidate's name. When all votes are read, you scan the entire board to see who has the most marks. It's simple but requires a lot of space on your whiteboard.

- **Boyer-Moore (Voting) Approach:** Imagine a room of voters where one candidate has a true majority. You tell everyone to find a person who voted for a different candidate and form a pair. Both people in the pair then sit down, effectively cancelling each other out. Because your candidate has more than half the votes, they are guaranteed to be the only ones left standing at the end.

👉 **Rule of thumb:** For both methods, doubling the input size doubles the time. However, the Boyer-Moore algorithm uses a tiny, fixed amount of memory, while the hash map approach requires more memory as the number of unique elements grows.

---

#### Conclusion

- **Efficiency:** Both solutions are optimally efficient in time. The Boyer-Moore algorithm is exceptionally efficient in space.

- **Limits & Trade-offs:** 
    - The **Hash Map** solution is more flexible. It can be easily adapted to solve related problems, like finding the top `k` frequent elements or if the majority element guarantee is removed. Its cost is memory.
    - The **Boyer-Moore** algorithm is a specialized, highly optimized solution for this *exact* problem. It is less adaptable but unbeatable on space efficiency.

- **Lesson Learned:** Sometimes, a clever observation about the problem's constraints (the majority element appears *more than n/2 times*) can unlock a vastly more efficient algorithm. The concept of "cancelling out" elements is a powerful pattern that can solve certain counting and voting problems without needing to store explicit counts.
