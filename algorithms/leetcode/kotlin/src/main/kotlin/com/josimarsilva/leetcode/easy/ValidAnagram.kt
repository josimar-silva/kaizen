package com.josimarsilva.leetcode.easy

/**
 * Given two strings s and t, return true if t is an anagram of s, and false otherwise.
 *
 * Constraints:
 * - 1 <= s.length, t.length <= 5 * 104
 * - s and t consist of lowercase English letters.
 *
 * Ref.: https://leetcode.com/problems/valid-anagram
 */
//Time O(N + M) Space: O(1)
fun isAnagram(s: String, t: String): Boolean {
    if (s.length != t.length) return false

    val availableLetters = IntArray(128)

    s.forEach { availableLetters[it.code]++ }

    for (char in t) {
        if (availableLetters[char.code] <= 0) return false
        availableLetters[char.code]--
    }

    return true
}


//Time O(N + M) Space: O(1)
fun isAnagramUsingMap(s: String, t: String): Boolean {
    if (s.length != t.length) return false

    val availableLetters = mutableMapOf<Char, Int>()

    s.forEach {
        availableLetters.compute(it) { letter, occurrences -> if (occurrences == null) 1 else occurrences + 1 }
    }

    for (char in t) {
        if (availableLetters[char] != null && availableLetters[char]!! > 0) {
            availableLetters[char] = availableLetters[char]!!.dec()
        } else {
            return false
        }
    }

    return true
}