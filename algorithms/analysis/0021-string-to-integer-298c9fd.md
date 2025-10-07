---
title: String to Integer (myAtoi)
commit: 298c9fd
url: https://github.com/josimar-silva/kaizen/commit/298c9fddc3e0e9f5a0fd8f35ed2fdf8dada99cc7
---

#### Problem
Implement the `myAtoi(string s)` function, which converts a string to a 32-bit signed integer according to specific rules: ignoring leading whitespace, determining signedness, converting digits, and rounding (clamping) the result to the 32-bit signed integer range `[-2^31, 2^31 - 1]`.

---

#### Big O Analysis

**Time Complexity:**  
- The algorithm iterates through the input string `s` once.
- Each character is processed with constant time operations (character checks, arithmetic, overflow checks).
- ⇒ **O(N)**, where `N` is the length of the input string.

**Space Complexity:**  
- The algorithm uses a fixed number of variables (`sign`, `convertedNumber`, `weHaveStarted`, loop indices).
- The memory required for these variables is constant and does not scale with the size of the input string.
- ⇒ **O(1)**

---

#### Layman’s Terms
git 
- The algorithm reads the input string character by character, from left to right.
- It first skips any leading empty spaces.
- Then, it looks for an optional sign (`+` or `-`).
- After that, it collects all consecutive digits, building up the number.
- As it builds the number, it constantly checks if the number is about to become too large or too small to fit into a standard 32-bit integer. If it is, it immediately stops and returns the maximum or minimum possible 32-bit integer.
- If no digits are found, or if an invalid character is encountered early, it returns 0.

👉 **Rule of thumb:** *The time it takes to convert the string grows directly with the length of the string. If the string is twice as long, it takes roughly twice as much time. The memory used remains tiny, regardless of the string's length.*

---

#### Conclusion

- **Efficiency:** The solution achieves optimal **O(N) time complexity** because it must inspect each character of the input string at most once. It also achieves optimal **O(1) space complexity** by using a fixed number of variables.
- **Limits:** The algorithm correctly handles the constraints of the 32-bit signed integer range and string length up to 200.
- **Improvements:** Key improvements during our session included:
    - Transitioning `convertedNumber` to a `Long` to safely handle intermediate values.
    - Implementing precise, incremental overflow checks *during* digit accumulation, preventing `Long` overflow and ensuring correct clamping to `Int.MAX_VALUE` or `Int.MIN_VALUE`.
    - Correctly applying the sign to each `digit` before accumulation to simplify overflow logic.
    - (Future improvement discussed: Refining the initial parsing order for whitespace and sign handling.)
- **Lesson Learned:** This problem is a classic example of the importance of meticulous edge case handling and numerical precision. It highlights how to:
    - Design an efficient single-pass algorithm.
    - Implement robust, incremental overflow detection for integer limits.
    - Carefully manage parsing state (e.g., `weHaveStarted`) to adhere to complex rules.

---