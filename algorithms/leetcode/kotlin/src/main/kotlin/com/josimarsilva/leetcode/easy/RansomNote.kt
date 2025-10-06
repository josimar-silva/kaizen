package com.josimarsilva.leetcode.easy

/**
 * Given two strings ransomNote and magazine,
 * return true if ransomNote can be constructed
 * by using the letters from magazine and false otherwise.
 *
 * Each letter in magazine can only be used once in ransomNote.
 *
 * Constraints:
 * - 1 <= ransomNote.length, magazine.length <= 105
 * - ransomNote and magazine consist of lowercase English letters.
 *
 * Ref.: https://leetcode.com/problems/ransom-note
 *
 */
class RansomNote {

    /**
     * Time: O(N + M): Sum of the two loops complexity
     * Space: O(1): The upperbound (worst-case scenario) is the 26 (maximum number of English letters)
     */
    fun canConstruct(ransomNote: String, magazine: String): Boolean {
        if(magazine.length < ransomNote.length) return false

        val lettersCount = IntArray(26)

        magazine.forEach { char -> lettersCount[char - 'a']++ }

        for (char in ransomNote) {
            val currentChar = char - 'a'
            lettersCount[currentChar]--
            if (lettersCount[currentChar] < 0) return false
        }

        return true
    }

    /**
     * Time: O(N + M): Sum of the two loops complexity
     * Space: O(1): The upperbound (worst-case scenario) is the 26 (maximum number of English letters)
     */
    fun canConstructWithMap(ransomNote: String, magazine: String): Boolean {
        if(magazine.length < ransomNote.length) return false

        val availableTokens = mutableMapOf<Char, Int>()

        magazine.forEach { char -> availableTokens[char] = availableTokens.getOrDefault(char, 0) +1 }

        for (char in ransomNote) {
            if(availableTokens[char] != null && availableTokens[char]!! > 0) {
                availableTokens[char] = availableTokens[char]!!.dec()
            } else {
                return false
            }
        }

        return true
    }

}