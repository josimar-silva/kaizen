package com.josimarsilva.projecteuler.java;

import com.josimarsilva.projecteuler.java.common.Range;

/**
 *  2520 is the smallest number that can be divided by each of the numbers from 1 to 10 without any remainder.
 *
 *  What is the smallest positive number that is evenly divisible by all the numbers from 1 to 20?
 *
 *  Ref.: https://projecteuler.net/problem=5
 */
public class SmallestMultiple {

    static SmallestMultiple find() {
        return new SmallestMultiple();
    }

    // Euclidean algorithm for Greater Common Divisor
    private long greaterCommonDivisor(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    private long lowestCommonDivisor(long a, long b) {
        return (a * b) / greaterCommonDivisor(a, b);
    }

    public long smallestMultipleWithin(Range range) {
            long result = 1;
            for (int i = range.start(); i <= range.end(); i++) {
                result = lowestCommonDivisor(result, i);
            }
            return result;
    }
}
