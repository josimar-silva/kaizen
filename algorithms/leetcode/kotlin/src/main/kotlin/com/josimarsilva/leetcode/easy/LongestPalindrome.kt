package com.josimarsilva.leetcode.easy

/**
 * Given a string s which consists of lowercase or uppercase letters, return the length of the longest palindrome that can be built with those letters.
 *
 * Letters are case sensitive, for example, "Aa" is not considered a palindrome.
 *
 * Constraints:
 * - 1 <= s.length <= 2000
 * - s consists of lowercase and/or uppercase English letters only.
 *
 * Ref.: https://leetcode.com/problems/longest-palindrome
 */
class LongestPalindrome {

    /**
     * Time: O(N)
     * Space: O(1)
     */
    fun longestPalindrome(string: String): Int {
        val unpaired = hashSetOf<Char>()
        var pairs = 0

        for (char in string) {
            if (unpaired.remove(char)) { pairs++ } else { unpaired.add(char) }
        }

        var length = pairs * 2
        if (unpaired.isNotEmpty()) { length++ }
        return length
    }

}