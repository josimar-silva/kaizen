package com.josimarsilva.leetcode.easy

/**
 * A phrase is a palindrome if, after converting all uppercase letters into lowercase letters
 * and removing all non-alphanumeric characters, it reads the same forward and backward.
 * Alphanumeric characters include letters and numbers.
 *
 * Given a string s, return true if it is a palindrome, or false otherwise.
 *
 * Constraints:
 *
 * - 1 <= s.length <= 2 * 105
 * - s consists only of printable ASCII characters.
 *
 * Ref.: https://leetcode.com/problems/valid-palindrome
 */
//Time: O(N) / Space: O(1)
fun isPalindrome(string: String): Boolean {
    if (string.isBlank()) return true

    var leftPointer = 0
    var rightPointer = string.length - 1

    while (leftPointer <= rightPointer) {
        while (leftPointer <= rightPointer && !string[leftPointer].isLetterOrDigit()) {
            leftPointer++
        }
        while (rightPointer >= leftPointer && !string[rightPointer].isLetterOrDigit()) {
            rightPointer--
        }

        if (leftPointer > rightPointer) break

        val left = string[leftPointer]
        val right = string[rightPointer]

        if (left.lowercase() != right.lowercase()) return false

        leftPointer++
        rightPointer--
    }

    return true
}


//Time: O(N) / Space: O(N * 3)
fun isPalindromeNaive(string: String): Boolean {
    if (string.isBlank()) return true

    val sanitized = string
        .lowercase() //+1 String
        .replace(Regex("[^a-z0-9]"), "") //+1 String

    return sanitized == sanitized.reversed() //+1 String
}