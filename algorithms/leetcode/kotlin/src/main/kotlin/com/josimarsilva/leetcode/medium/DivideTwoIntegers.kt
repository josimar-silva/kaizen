package com.josimarsilva.leetcode.medium

/**
 *
 * Given two integers dividend and divisor, divide two integers without using multiplication, division, and mod operator.
 *
 * The integer division should truncate toward zero, which means losing its fractional part.
 * For example, 8.345 would be truncated to 8, and -2.7335 would be truncated to -2.
 *
 * Return the quotient after dividing dividend by divisor.
 *
 * Note: Assume we are dealing with an environment that could only store integers within the 32-bit signed integer range: [−231, 231 − 1].
 * For this problem, if the quotient is strictly greater than 231 - 1, then return 231 - 1,
 * and if the quotient is strictly less than -231, then return -231.
 *
 * Constraints:
 *
 * 1. -231 <= dividend, divisor <= 231 - 1
 * 2. divisor != 0
 *
 * Ref.: https://leetcode.com/problems/divide-two-integers/
 */
class DivideTwoIntegers {
    fun divide(dividend: Int, divisor: Int): Int {
        if (divisor == 0) throw ArithmeticException("divisor can not be zero")
        if (dividend == Int.MIN_VALUE && divisor == -1) return Int.MAX_VALUE
        if (dividend == Int.MIN_VALUE && divisor == 1) return Int.MIN_VALUE

        val isNegative = isNegative(dividend, divisor)
        var currentDividend = if (dividend > 0) -dividend else dividend
        val flippedDivisor = if (divisor > 0) -divisor else divisor

        var quotient = 0

        while (currentDividend <= flippedDivisor) {
            var tempDivisor = flippedDivisor
            var multiple = 1
            while (currentDividend <= (tempDivisor shl 1) && (tempDivisor shl 1 < 0)) {
                tempDivisor = tempDivisor shl 1
                multiple = multiple shl 1
            }
            currentDividend -= tempDivisor
            quotient += multiple
        }

        return if(isNegative) -quotient else quotient
    }

    private fun isNegative(dividend: Int, divisor: Int) =
        (dividend > 0 && divisor < 0) || (dividend < 0 && divisor > 0)
}

