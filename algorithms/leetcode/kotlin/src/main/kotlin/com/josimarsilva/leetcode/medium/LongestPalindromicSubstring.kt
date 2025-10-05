package com.josimarsilva.leetcode.medium


/**
 *
 * Given a string s, return the longest palindromic substring in s.
 * A string is palindromic if it reads the same forward and backward.
 *
 * Ref.: https://leetcode.com/problems/longest-palindromic-substring
 *
 * Constraints:
 * - 1 <= s.length <= 1000
 * - s consist of only digits and English letters.
 *
 */
class LongestPalindromicSubstring {

    /**
     * Time: O(N^2)
     * Space: O(N^2)
     */
    fun longestPalindrome(string: String): String {
        if (string.isEmpty()) return string
        if (string.length == 1) return string

        val n = string.length
        val combinations = Array(string.length) { BooleanArray(string.length) }
        var longestPalindromeStart = 0
        var longestPalindromeLength = 1

        // Fill Base 1 cases
        for (i in 0..n-1) {
            combinations[i][i] = true
        }

        // Fill Base 2 cases
        for (i in 0..n-2) {
            combinations[i][i+1] = string[i] == string[i+1]
            if(combinations[i][i+1]) {
                longestPalindromeStart = i
                longestPalindromeLength = 2
            }
        }

        for (length in 3..n) {
            for (i in 0..n - length) {
                val j = i + length - 1
                val isPalindrome = (string[i] == string[j]) && combinations[i+1][j-1]
                combinations[i][j] = isPalindrome

                if(isPalindrome) {
                    longestPalindromeStart = i
                    longestPalindromeLength = length
                }
            }
        }

        return string.substring(longestPalindromeStart, longestPalindromeStart + longestPalindromeLength)
    }

    /**
     * Time: O(N^3) -> O(N^2) loops * O(N) filter operation
     * Space: O(N^2)
     */
    fun longestPalindromeBruteForceImplementation(string: String): String {
        if (string.isEmpty()) return string
        if (string.length == 1) return string

        val substrings = mutableListOf<String>()

        for (i in 0..string.length - 1) {
            for (j in i..string.length) {
                substrings.add(string.substring(i, j))
            }
        }

        val palindromes = substrings.filter { it == it.reversed() }

        return if(palindromes.isEmpty()) "" else palindromes.maxBy { it.length }
    }

}