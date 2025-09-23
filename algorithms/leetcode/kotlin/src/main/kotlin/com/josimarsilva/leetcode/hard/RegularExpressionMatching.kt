package com.josimarsilva.leetcode.hard

/**
 * Given an input string s and a pattern p, implement regular expression matching with support for '.' and '*' where:
 *
 * '.' Matches any single character.
 * '*' Matches zero or more of the preceding element.
 *
 *  The matching should cover the entire input string (not partial).
 *
 *  Constraints:
 *
 * - 1 <= s.length <= 20
 * - 1 <= p.length <= 20
 * - s contains only lowercase English letters.
 * - p contains only lowercase English letters, '.', and '*'.
 * - It is guaranteed for each appearance of the character '*', there will be a previous valid character to match.
 *
 * Ref.: https://leetcode.com/problems/regular-expression-matching/
 */
class RegularExpressionMatching {

    //memoization
    private val cache = mutableMapOf<String, Boolean>()

    companion object {
        const val DOT = '.'
        const val STAR = '*'
    }

    fun isMatch(string: String, pattern: String): Boolean {
        val key = keyOf(string, pattern)

        val value = cache[key]
        if (value != null) {
            return value
        }

        val matches: Boolean = if (string.isEmpty() && pattern.isEmpty()) {
            true
        } else if (string.isNotEmpty() && pattern.isEmpty()) {
            false
        } else if (string.isEmpty() && pattern.isNotEmpty()) {
            handleEmptyStringNonEmptyPattern(string, pattern)
        } else {
            handleNonEmptyStringAndPattern(string, pattern)
        }

        cache[key] = matches
        return matches
    }

    private fun handleEmptyStringNonEmptyPattern(string: String, pattern: String): Boolean {
        return if (pattern.length >= 2 && pattern[1] == STAR) {
            isMatch(string, pattern.substring(2))
        } else {
            false
        }
    }

    private fun handleNonEmptyStringAndPattern(string: String, pattern: String): Boolean {
        val firstCharInString = string[0]
        val firstCharInPattern = pattern[0]

        val firstCharMatches = firstCharInString == firstCharInPattern || firstCharInPattern == DOT

        return if (pattern.length >= 2 && pattern[1] == STAR) {
            handleStarCase(string, pattern, firstCharMatches)
        } else {
            handleSimpleMatch(string, pattern, firstCharMatches)
        }
    }

    // Option 1: x* matches zero occurrences of x
    // Option 2: x* matches one or more occurrences of x
    private fun handleStarCase(string: String, pattern: String, firstCharMatches: Boolean): Boolean {
        return isMatch(string, pattern.substring(2)) ||
                (firstCharMatches && isMatch(string.substring(1), pattern))
    }

    private fun handleSimpleMatch(string: String, pattern: String, firstCharMatches: Boolean): Boolean {
        return if (firstCharMatches) {
            isMatch(string.substring(1), pattern.substring(1))
        } else {
            false
        }
    }

    private fun keyOf(string: String, pattern: String) = "${string}-${pattern}"
}
