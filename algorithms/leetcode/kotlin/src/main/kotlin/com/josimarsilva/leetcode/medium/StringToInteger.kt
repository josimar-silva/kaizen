package com.josimarsilva.leetcode.medium

/**
 * Implement the myAtoi(string s) function, which converts a string to a 32-bit signed integer.
 *
 * The algorithm for myAtoi(string s) is as follows:
 *
 * Whitespace: Ignore any leading whitespace (" ").
 * Signedness: Determine the sign by checking if the next character is '-' or '+', assuming positivity if neither present.
 * Conversion: Read the integer by skipping leading zeros until a non-digit character is encountered or the end of the string is reached.
 * If no digits were read, then the result is 0.
 * Rounding: If the integer is out of the 32-bit signed integer range [-231, 231 - 1], then round the integer to remain in the range.
 * Specifically, integers less than -231 should be rounded to -231, and integers greater than 231 - 1 should be rounded to 231 - 1.
 *
 * Return the integer as the final result.
 *
 * Constraints:
 * - 0 <= s.length <= 200
 * - s consists of English letters (lower-case and upper-case), digits (0-9), ' ', '+', '-', and '.'.
 *
 */
class StringToInteger {

    /**
     * Time: O(N)
     * Space: O(1)
     */
    fun myAtoi(stringToConvert: String): Int {
        if (stringToConvert.isEmpty()) return 0
        if (stringToConvert[0].isLetter()) return 0
        if (stringToConvert[0] == '.') return 0

        var weHaveStarted = false
        var sign = +1
        var convertedNumber = 0L

        for ((index, char) in stringToConvert.withIndex()) {
            if (char.isLetter()) break
            if (weHaveStarted && (char.isWhitespace() || isSignChar(char))) break
            if (char.isWhitespace()) continue

            if (!weHaveStarted && isSignChar(char)) {
                weHaveStarted = true
                if (char == '-') {
                    sign = -1
                }
                continue
            }

            if (char.isDigit()) {
                val digit = char.toString().toInt() * sign

                if(convertedNumber > Int.MAX_VALUE / 10) return Int.MAX_VALUE
                if(convertedNumber == Int.MAX_VALUE.toLong() / 10 && digit > Int.MAX_VALUE % 10) return Int.MAX_VALUE

                if(convertedNumber < Int.MIN_VALUE / 10) return Int.MIN_VALUE
                if(convertedNumber == Int.MIN_VALUE.toLong() / 10 && digit < Int.MIN_VALUE % 10) return Int.MIN_VALUE

                convertedNumber = convertedNumber * 10 + digit
            }

            if (stringToConvert.length - 1 > index && !stringToConvert[index + 1].isDigit()) break
        }

        return convertedNumber.toInt()
    }

    private fun isSignChar(char: Char): Boolean = (char == '-' || char == '+')

}