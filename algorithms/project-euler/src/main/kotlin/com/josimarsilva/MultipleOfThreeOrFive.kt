package com.josimarsilva

/**
 * <p> If we list all the natural numbers below 10 that are multiples of 3 or 5, we get 3, 5, 6 and 9.
 * The sum of these multiples is 23.
 * Find the sum of all the multiples of 3 or 5 below 1000. </p>
 * <p> <b> https://projecteuler.net/problem=1 </b> </p>
 */
class MultipleOfThreeOrFive {

    fun sumUpTo(max: Int): Int {
        var sum = 0

        (1..<max).forEach { number: Int ->
            if (isMultipleOfThreeOrFive(number)) {
                sum += number
            }
        }

        return sum
    }

    private fun isMultipleOfThreeOrFive(number: Int) = ((number % 3) == 0 || (number % 5) == 0)

}
