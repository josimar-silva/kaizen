package com.josimarsilva.leetcode.easy

/**
 * Given an integer x, return true if x is a palindrome, and false otherwise.
 *
 * Constraints:
 *
 * -231 <= x <= 231 - 1
 *
 * Ref.: https://leetcode.com/problems/palindrome-number/descriptio
 */
class PalindromeNumber {
    fun isPalindrome(x: Int): Boolean {
        if (x == 0) return true
        if (x < 0) return false
        if (x % 10 == 0) return false

        var y = x
        var reversedNumber = 0

        while (reversedNumber < y) {
            val lastDigit = y % 10
            reversedNumber = reversedNumber * 10 + lastDigit
            y = y / 10
        }

        if (y == reversedNumber) return true


        return y == reversedNumber / 10
    }
}