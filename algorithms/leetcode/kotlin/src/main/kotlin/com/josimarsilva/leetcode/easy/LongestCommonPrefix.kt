package com.josimarsilva.leetcode.easy

/**
 * Write a function to find the longest common prefix string amongst an array of strings.
 *
 * If there is no common prefix, return an empty string "".
 *
 * Constraints:
 *
 * - 1 <= strs.length <= 200
 * - 0 <= strs[i].length <= 200
 * - strs[i] consists of only lowercase English letters if it is non-empty.
 *
 * Ref.: https://leetcode.com/problems/longest-common-prefix
 */
class LongestCommonPrefix {
    fun longestCommonPrefix(strings: Array<String>): String {
        if (strings.isEmpty()) return ""

        var longestCommonPrefix = strings.first()

        for (i in 1..<strings.size) {
            while (!strings[i].startsWith(longestCommonPrefix)) {
                longestCommonPrefix = longestCommonPrefix.subSequence(0, longestCommonPrefix.length - 1).toString()
                if (longestCommonPrefix.isEmpty()) {
                    return ""
                }
            }
        }

        return longestCommonPrefix
    }
}