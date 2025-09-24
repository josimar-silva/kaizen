package com.josimarsilva.leetcode.easy

/**
 * Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
 *
 * An input string is valid if:
 *
 * 1. Open brackets must be closed by the same type of brackets.
 * 2. Open brackets must be closed in the correct order.
 * 3. Every close bracket has a corresponding open bracket of the same type.
 *
 * Constraints:
 *
 * - 1 <= s.length <= 104
 * - s consists of parentheses only '()[]{}'.
 *
 * Ref.: https://leetcode.com/problems/valid-parentheses
 */
class ValidParentheses {
    fun isValid(string: String): Boolean {
        if (string.isEmpty()) return false
        if (hasOddLength(string)) return false
        val stack = ArrayDeque<Char>(string.length / 2)

        for (char in string) {
            when (char) {
                '(', '[', '{' -> stack.add(closeParenthesesOf(char))
                ')', ']', '}' -> {
                    if (stack.isEmpty() || stack.removeLast() != char) {
                        return false
                    }
                }

                else -> {}
            }
        }

        return stack.isEmpty()
    }

    private fun hasOddLength(string: String) = string.length % 2 == 1

    private fun closeParenthesesOf(char: Char) =
        when (char) {
            '(' -> ')'
            '[' -> ']'
            else -> '}'
        }
}
