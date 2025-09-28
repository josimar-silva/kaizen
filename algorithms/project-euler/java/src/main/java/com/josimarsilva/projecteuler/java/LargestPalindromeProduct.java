package com.josimarsilva.projecteuler.java;

/**
 * A palindromic number reads the same both ways.
 * The largest palindrome made from the product of two-digit numbers is 9009 = 91 x 99.
 *
 * Find the largest palindrome made from the product of two 3-digit numbers.
 *
 * Ref.: <a href="https://projecteuler.net/problem=4">Reference</a>
 *
 */
public class LargestPalindromeProduct {

    public static long largestPalindromeProductWith(int numberOfDigits) {
        if (numberOfDigits <= 0) return 0;
        var lowerBound = (long) Math.pow(10, numberOfDigits - 1);
        var upperBound = (long) Math.pow(10, numberOfDigits) - 1;
        var largestPalindrome = 0L;

        for (long i = upperBound; i >= lowerBound; i--) {
            for (long j = i; j >= lowerBound; j--) {
                long product = i * j;
                if (product <= largestPalindrome) {
                    break;
                }
                if (isPalindrome(product)) {
                    largestPalindrome = product;
                }
            }
        }
        return largestPalindrome;
    }

    private static long reverse(long number) {
        var reversed = 0L;
        var seedNumber = number;
        while (seedNumber > 0) {
            reversed = 10 * reversed + seedNumber % 10;
            seedNumber = seedNumber / 10;
        }
        return reversed;
    }

    private static boolean isPalindrome(long number) {
        if (number < 0)  return false;
        return number == reverse(number);
    }

}