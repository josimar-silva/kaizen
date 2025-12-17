package com.josimarsilva.leetcode.easy

/**
 *
 * Given two strings s and t, return true if s is a subsequence of t, or false otherwise.
 *
 * A subsequence of a string is a new string that is formed from the original string by deleting some (can be none)
 * of the characters without disturbing the relative positions of the remaining characters.
 * (i.e., "ace" is a subsequence of "abcde" while "aec" is not).
 *
 * Constraints:
 * - 0 <= s.length <= 100
 * - 0 <= t.length <= 104
 * - s and t consist only of lowercase English letters.
 *
 * Follow up: Suppose there are lots of incoming s, say s1, s2, ..., sk where k >= 109,
 * and you want to check one by one to see if t has its subsequence.
 * In this scenario, how would you change your code?
 *
 * Ref.: https://leetcode.com/problems/is-subsequence/description/
 */
//Time: O(N) -> Where N is the length of the target
//Space: O(1) -> Function created a constant number of variables
fun isSubsequence(string: String, target: String): Boolean {
    if (string.isEmpty()) return true

    var stringPointer = 0
    var targetPointer = 0

    while (stringPointer < string.length && targetPointer < target.length) {
        if (string[stringPointer] == target[targetPointer]) {
            stringPointer++ // Char found, we move to the next one
        }

        targetPointer++ // Move char to next position to compare with search string
    }

    return stringPointer == string.length //We successfully found all char from string on target?
}

//Time: O(T + K * S * log L)
// where:
//   T -> Length of the `target` string.
//   K -> Number of `strings` in the input list.
//   S -> Average length of a `string` in the input list.
//   L -> Maximum number of occurrences of any character in `target` (L <= T).
//
//Space: O(T) Where T is the target length
fun isSubsequenceFollowUp(strings: List<String>, target: String): Boolean {
    if (strings.isEmpty()) return true

    val dictionary = buildDictionaryOf(target)

    for (string in strings) {
        var indexOfLastKnowChar = -1

        for (c in string) {
            val indexes = dictionary[c]

            if (indexes == null) { return false } // given char is not present on target

            val searchResult = indexes.binarySearch(indexOfLastKnowChar + 1)

            //
            val insertionPoint = if (searchResult < 0) -(searchResult + 1) else searchResult

            // The search could not find an index within bounds for this character
            // Meaning the char is not available in target
            if (insertionPoint > indexes.size - 1) {
                return false
            }

            indexOfLastKnowChar = indexes[insertionPoint]
        }
    }

    return true
}


// Time: O(T) Where T is the target length
// Space: O(N)
private fun buildDictionaryOf(target: String): MutableMap<Char, MutableList<Int>> {
    val dictionary = mutableMapOf<Char, MutableList<Int>>()

    target.forEachIndexed { index, char ->
        val indexes = dictionary.getOrPut(char) { mutableListOf<Int>() }
        indexes.add(index)
    }

    return dictionary
}

