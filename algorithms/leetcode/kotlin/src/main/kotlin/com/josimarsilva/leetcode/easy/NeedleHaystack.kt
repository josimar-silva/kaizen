package com.josimarsilva.leetcode.easy

/**
 * Given two strings needle and haystack, return the index of the first occurrence of needle in haystack,
 * or -1 if needle is not part of haystack.
 *
 * Constraints
 * - 1 <= haystack.length, needle.length <= 104
 * - haystack and needle consist of only lowercase English characters.
 *
 * Ref.: https://leetcode.com/problems/find-the-index-of-the-first-occurrence-in-a-string
 */
//Time: O(N*M) / Space: (M) (M=Needle Size)
fun strStrNaive(haystack: String, needle: String): Int {
    if (needle.isBlank()) return 0
    if (haystack.isBlank()) return -1

    var leftPointer = 0
    var rightPointer = needle.length

    while (rightPointer < haystack.length + 1) {
        val possibleNeedle = haystack.substring(leftPointer, rightPointer)

        if (possibleNeedle == needle) return leftPointer

        leftPointer++
        rightPointer++
    }

    return -1
}

//Time: O(N*M) / Space: (M) (M=Needle Size)
fun strStrNative(haystack: String, needle: String) = haystack.indexOf(needle)
