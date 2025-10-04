package com.josimarsilva.leetcode.easy

/**
 * Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.
 * Symbol       Value
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 *
 * For example, 2 is written as II in Roman numeral, just two ones added together.
 * 12 is written as XII, which is simply X + II.
 * The number 27 is written as XXVII, which is XX + V + II.
 *
 * Roman numerals are usually written largest to smallest from left to right.
 * However, the numeral for four is not IIII.
 * Instead, the number four is written as IV.
 * Because the one is before the five we subtract it making four.
 * The same principle applies to the number nine, which is written as IX.
 * There are six instances where subtraction is used:
 *
 * I can be placed before V (5) and X (10) to make 4 and 9.
 * X can be placed before L (50) and C (100) to make 40 and 90.
 * C can be placed before D (500) and M (1000) to make 400 and 900.
 * Given a roman numeral, convert it to an integer.
 *
 * Constraints:
 * - 1 <= s.length <= 15
 * - s contains only the characters ('I', 'V', 'X', 'L', 'C', 'D', 'M').
 * - It is guaranteed that s is a valid roman numeral in the range [1, 3999].
 */
class RomanToInteger {

    val dictionary = mapOf<Char, Int>(
        'I' to 1,
        'V' to 5,
        'X' to 10,
        'L' to 50,
        'C' to 100,
        'D' to 500,
        'M' to 1000
    )

    /**
     * We leverage Roman numerals order to simplify the subtraction cases.
     * Time: O(N)
     * Space: O(1)
     */
    fun romanToInt(romanNumber: String): Int {
        var integer = 0
        var previousValue = 0

        for (i in romanNumber.length - 1 downTo 0) {
            val currentChar = romanNumber[i]
            var currentValue = dictionary[currentChar]!!

            if(currentValue < previousValue) {
                integer -= currentValue
            } else {
                integer += currentValue
            }

            previousValue = currentValue
        }

        return integer
    }

    /**
     * Time: O(N)
     * Space: O(1)
     */
    fun romanToIntBruteForceImplementation(romanNumber: String): Int {
        var integer = 0

        for ((index, char) in romanNumber.withIndex()) {
            var converted = dictionary[char]!!
            val nextChar = minOf(index + 1, romanNumber.length - 1)

            when(char) {
                'I' -> {
                    if(romanNumber[nextChar] == 'V' || romanNumber[nextChar] == 'X') {
                        converted *= -1
                    }
                }
                'X' -> {
                    if(romanNumber[nextChar] == 'L' || romanNumber[nextChar] == 'C') {
                        converted *= -1
                    }
                }
                'C' -> {
                    if(romanNumber[nextChar] == 'D' || romanNumber[nextChar] == 'M') {
                        converted *= -1
                    }
                }
            }


            integer += converted
        }

        return integer
    }


}