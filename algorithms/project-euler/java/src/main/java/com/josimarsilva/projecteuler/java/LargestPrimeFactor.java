package com.josimarsilva.projecteuler.java;

/**
 * The prime factors of 13195 are 5, 7, 13 and 29.
 * What is the largest prime factor of the number 600851475143?
 *
 * Ref.: <a href="https://projecteuler.net/problem=3">Problem</a>
 *
 */
public class LargestPrimeFactor {

    public static long largestPrimeFactorOf(long number) {
        if(number < 2) throw new IllegalArgumentException("Number '" + number + "' is invalid.");

        var toFactor = number;
        var divider = 2L;
        var largestKnowFactor = 0L;

        /**
         * For any pair of factors (a, b) that multiply to a number
         *   n, one factor will always be less than or equal to the square root of n, and the other will be greater
         *   than or equal to it.
         */
        while ((divider * divider) < toFactor) {
            if(toFactor % divider == 0) {
                largestKnowFactor = divider;
                toFactor = toFactor / divider;
            } else {
                divider++;
            }

        }

        return Math.max(largestKnowFactor, toFactor);
    }
}
