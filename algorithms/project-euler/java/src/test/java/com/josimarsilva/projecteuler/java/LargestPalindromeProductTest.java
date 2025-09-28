package com.josimarsilva.projecteuler.java;

import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.josimarsilva.projecteuler.java.LargestPalindromeProduct.largestPalindromeProductWith;
import static org.assertj.core.api.Assertions.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LargestPalindromeProductTest {

    @ParameterizedTest
    @MethodSource("samples")
    public void shouldReturnTheLargestPalindromeProductOfGivenDigits(int digits, long expectedLargestPalindrome) {
        assertThat(largestPalindromeProductWith(digits)).isEqualTo(expectedLargestPalindrome);
    }

    private Stream<Arguments> samples() {
        return Stream.of(
                Arguments.of(2, 9009L),
                Arguments.of(3, 906_609L),
                Arguments.of(4, 99_000_099L)
        );
    }

}