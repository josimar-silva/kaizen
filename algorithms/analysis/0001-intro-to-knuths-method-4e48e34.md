## Algorithm: Random Word (Knuth’s Method / Reservoir Sampling)

**Source:** [Coursera](https://www.coursera.org/learn/algorithms-part1)  
**Commit:** [4e48e34](https://github.com/josimar-silva/kaizen/commit/4e48e34a4372e6015c2af1655438b72534f32fc6)  
**Language:** Java  

---

### Problem
Write a program `RandomWord.java` that reads a sequence of words from standard input and prints **one of them uniformly at random**.  
Constraints:  
- Do not store the words in an array or list.  
- Use Knuth’s method (reservoir sampling): when reading the *i*-th word, select it with probability `1/i` as the new champion.  

---

### Big O Analysis

**Time Complexity:**  
- Reads each word once.  
- For each word: constant work (`bernoulli(1/i)` + assignment).  
- ⇒ **O(n)** time, where `n` = number of words.  

**Space Complexity:**  
- Stores only 1 variable (`champion`).  
- ⇒ **O(1)** space.  

---

### Layman’s Terms

- The program looks at each word **once and only once**.  
- It doesn’t remember all the words — just the current “champion.”  
- When a new word comes in, it gets a fair chance to replace the champion.  
- By the end, every word has the **same chance** of being picked, no matter how long the input was.  

👉 **Scales perfectly**:  
- With 10 words → 10 operations.  
- With 1,000,000 words → 1,000,000 operations (still linear).  
- Memory use is constant — like carrying only **one note card** instead of storing the whole book.  

---

### Reflection

This is a textbook example of an **elegant algorithm**:  
- Efficient (linear time, constant space).  
- Surprisingly fair (each item gets equal probability).  
- Useful when inputs are huge or infinite streams (e.g. live data).  

💡 **Lesson Learned:** Knuth’s method shows that randomness + probability can replace brute-force storage, making algorithms both simple and scalable.

---
