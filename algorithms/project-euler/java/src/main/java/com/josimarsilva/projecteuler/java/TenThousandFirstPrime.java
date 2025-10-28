package com.josimarsilva.projecteuler.java;

/**
 * By listing the first six prime numbers: 2,3,5,7, 11, and 13, we can see that the 6th prime is 13.
 *
 * What is the 10001st prime number?
 *
 * Ref.: <a href="https://projecteuler.net/problem=7">Reference</a>
 *
 */
record TenThousandFirstPrime(long position) {

    static TenThousandFirstPrime at(long position) {
        return new TenThousandFirstPrime(position);
    }

    private boolean isPrime(long number) {
        if (number == 0 || number == 1) return false;
        if (number == 2) return true;
        if (number % 2 == 0) return false;

        final var squareOfN = (long) Math.sqrt((double) number);
        for (long x = 3L; x <= squareOfN; x += 2) {
            if (number % x == 0) return false;
        }
        return true;
    }

    //Time: O(N ^ 3/2 (log N) ^ 3/2) / Space: O(1)
    long calculate() {
        var primeCount = 0L;
        var lastKnownPrime = 0L;
        var number = 2L;

        while (primeCount != position) {
            if (isPrime(number)) {
                lastKnownPrime = number;
                primeCount++;
            }
            number++;
        }

        return lastKnownPrime;
    }

}
