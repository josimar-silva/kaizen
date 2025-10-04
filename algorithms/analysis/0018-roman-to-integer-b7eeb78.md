---
title: Roman to Integer
commit: b7eeb78
url: https://github.com/josimar-silva/kaizen/commit/b7eeb780f8de984f65062f945b2991df45d2c27b
---

#### Problem
Given a roman numeral string, convert it to an integer.  
The core challenge is that while numerals are usually written largest-to-smallest (e.g., `XII` = 10 + 2), there are six subtractive cases where a smaller numeral precedes a larger one (e.g., `IV` = 5 - 1).

---

#### Big O Analysis
Two primary approaches were explored:

**1. Left-to-Right (Look-Ahead)**
- **Time Complexity:** Iterates through the string once. ⇒ **O(N)**
- **Space Complexity:** Uses a few constant-size variables. ⇒ **O(1)**

**2. Right-to-Left (Look-Behind)**
- **Time Complexity:** Iterates through the string once. ⇒ **O(N)**
- **Space Complexity:** Uses a few constant-size variables. ⇒ **O(1)**

---

#### Layman’s Terms
- **Left-to-Right Method:** Walks through the numeral from the start. If a small value appears before a larger one (like `I` before `V`), it treats the `I` as negative (-1) and adds it to the total. Otherwise, it just adds the value. This requires looking ahead to decide the sign.

- **Right-to-Left Method:** Walks through the numeral from the end. It remembers the value of the previous character. If the current character's value is smaller than the previous one, it's subtracted from the total. Otherwise, it's added. This is simpler because you only need to remember the past.

👉 **Rule of thumb:** *For both methods, if you double the length of the Roman numeral, the time it takes to convert it also doubles. From a performance perspective, they are identical.*

---

#### Conclusion

- **Efficiency:** Both O(N) time and O(1) space solutions are optimal. Performance is not a deciding factor between them.
- **Limits:** Given the problem constraints (string length <= 15), neither approach has any practical limits.
- **Improvements:** The main improvement was choosing the more readable and maintainable algorithm. The right-to-left approach is cleaner as it avoids complex conditional logic.
- **Lesson Learned:** This problem is a perfect case study in how changing the **direction of iteration** can transform an algorithm. A "look-ahead" problem with multiple special cases was reframed as a "look-behind" problem with a single, simple rule. This highlights the trade-off between an explicit but verbose implementation and a more elegant one that relies on the inherent structure of the input data.

---