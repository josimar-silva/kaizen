package com.josimarsilva.projecteuler.java;

/**
 *
 * The sum of the squares of the first ten natural numbers is 385.
 *
 * The square of the sum of the first ten natural numbers is 3035.
 *
 * Hence the difference between the sum of the squares of the first ten natural numbers
 * and the square of the sum is 3025 - 285 = 2640.
 *
 * Find the difference between the sum of the squares of the first one hundred natural numbers
 * and the square of the sum.
 *
 * Ref.: <a href="https://projecteuler.net/problem=6" />
 *
 * @param limit
 */
record SumSquareDifference(long limit) {

    static SumSquareDifference of(long limit) {
        return new SumSquareDifference(limit);
    }

    //Time: O(1) Space: O(1)
    long calculate() {
        var sumOfSquare = limit * (limit + 1) * (2 * limit + 1) / 6;
        var sumUpToLimit = (limit * (limit + 1) / 2);
        var squareOfSum = sumUpToLimit * sumUpToLimit;

        return squareOfSum - sumOfSquare;
    }

    //Time: O(N) Space: O(1)
    long calculateNaive() {
        var sumOfSquare = 0L;
        var squareOfSum = 0L;
        for (long i = 1; i <= limit; i++) {
            sumOfSquare += i * i;
            squareOfSum += i;
        }

        squareOfSum = squareOfSum * squareOfSum;

        return squareOfSum - sumOfSquare;
    }

}
