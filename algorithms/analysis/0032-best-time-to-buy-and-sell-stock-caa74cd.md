---
title: Best Time to Buy and Sell Stock
commit: caa74cd
url: https://github.com/josimar-silva/kaizen/commit/caa74cd29c2d0ccf53420e2ebc1a70e0ad1e6a49
---

#### Problem
Given an array of stock prices for consecutive days, find the maximum profit that can be made by buying on one day and selling on a future day. If no profit is possible, the result is 0.

---

### Approach 1: Brute Force

A straightforward way to solve the problem is to consider every possible pair of buy and sell days.

1.  Iterate through each day `i` as a potential buy day.
2.  In a nested loop, iterate through every subsequent day `j` as a potential sell day.
3.  Calculate the profit `prices[j] - prices[i]` and keep track of the maximum profit found.

#### Big O Analysis

**Time Complexity:**
- The outer loop runs `N` times, and the inner loop runs, on average, `N/2` times.
- This results in a nested loop structure.
- ⇒ **O(N²)**

**Space Complexity:**
- This method only requires a few variables to store the max profit and loop indices.
- ⇒ **O(1)**

---

### Approach 2: Single Pass (Greedy Approach)

An optimal solution that finds the maximum profit in a single pass through the price list. The key insight is that to calculate the best profit from selling on the current day, we only need to know one thing about the past: the lowest price seen so far.

We iterate through the list, keeping track of two variables:
1.  `lowestPriceSoFar`
2.  `maxProfitSoFar`

For each price, we first update `lowestPriceSoFar` if the current price is a new low. Then, we calculate the potential profit (`currentPrice - lowestPriceSoFar`) and update `maxProfitSoFar` if this potential profit is a new high.

#### Big O Analysis

**Time Complexity:**
- The algorithm iterates through the array of prices exactly once.
- ⇒ **O(N)**

**Space Complexity:**
- This method only requires two variables to store the running state (`lowestPriceSoFar` and `maxProfitSoFar`).
- The memory usage is constant.
- ⇒ **O(1)**

---

#### Layman’s Terms

- The **Brute Force** approach is like taking a calendar of prices, pointing to the first day, and then manually comparing it with every single day that follows. Then you move to the second day and repeat the whole process. For a year of prices, this is a huge amount of work.
- The **Single Pass** approach is like walking through the calendar one day at a time. You only need a sticky note where you write down the lowest price you've seen. Each day, you check if you should update your sticky note or if selling today would give you a new record profit. It's much faster because you never look back.

👉 **Rule of thumb:** For 10,000 days of prices, the O(N²) approach would do roughly 50,000,000 comparisons. The O(N) approach would do only 10,000.

---

#### Conclusion

- **Efficiency:** The O(N²) brute-force solution is too slow for the problem's constraints (up to 10^5 elements). The O(N) single-pass solution is highly efficient and optimal.
- **Lesson Learned:** Many optimization problems can be simplified by identifying the minimal state needed to carry forward through an iteration. Instead of reconsidering all past data at every step, we found that only the `lowest_price_so_far` was needed. This insight transformed an inefficient O(N²) algorithm into a lean O(N) one.

---
