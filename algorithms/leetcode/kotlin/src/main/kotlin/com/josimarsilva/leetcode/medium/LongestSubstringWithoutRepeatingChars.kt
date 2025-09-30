package com.josimarsilva.leetcode.medium

/**
 * Given a string s, find the length of the longest substring without duplicate characters.
 *
 * Constraints:
 * - 0 <= s.length <= 5 * 104
 * - s consists of English letters, digits, symbols and spaces.
 *
 * Ref.: https://leetcode.com/problems/longest-substring-without-repeating-characters
 */
class LongestSubstringWithoutRepeatingChars {

    fun lengthOfLongestSubstring(string: String): Int {
        if (string.isEmpty()) return 0
        val slidingWindow = mutableMapOf<Char, Int>()

        var maxLength = 0
        var leftPointer = 0

        for ((rightPointer, char) in string.withIndex()) {
            if (slidingWindow[char] != null) {
                leftPointer = maxOf(slidingWindow[char]!! + 1, leftPointer)
            }

            slidingWindow[char] = rightPointer
            val currentLength = rightPointer - leftPointer + 1
            maxLength = maxOf(currentLength, maxLength)
        }

        return maxLength
    }

    /**
     * Time: O(k * n) -> k: uniqueString.contains(lastKnowChar) * n: string.length
     * Space: O(1)
     */
    fun bruteForceImplementation(string: String): Int {
        if (string.isEmpty()) return 0;

        var uniqueSubstring = string[0].toString()
        var knowSubstrings = ArrayList<String>(string.length)

        for (i in 1..<string.length) {
            val lastKnowChar = string[i]
            if(uniqueSubstring.contains(lastKnowChar)) {
                knowSubstrings.add(uniqueSubstring)
                uniqueSubstring = lastKnowChar.toString()
            } else {
                uniqueSubstring += lastKnowChar
            }
        }

        return knowSubstrings.maxBy { it.length }.length
    }

}