package com.josimarsilva.leetcode.easy

import kotlin.math.floor

/**
 *
 * Given two binary strings a and b, return their sum as a binary string.
 *
 * Constraints:
 * - 1 <= a.length, b.length <= 104
 * - a and b consist only of '0' or '1' characters.
 * - Each string does not contain leading zeros except for the zero itself.
 *
 * Ref.: https://leetcode.com/problems/add-binary
 *
 */
// Time O(N+M) Space: O(N+M)
fun addBinary(a: String, b: String): String {
    var i = a.length - 1
    var j = b.length - 1
    var result = StringBuilder("")
    var carry = 0

    while (i >= 0 || j >=0 || carry != 0) {
        val x = (if(i >= 0) a[i] - '0' else 0)
        val y = (if(j >= 0) b[j] - '0' else 0)

        val sum = x + y + carry
        result.append(sum % 2)
        carry = sum / 2

        i--
        j--
    }

    return result.reverse().toString()
}