package com.josimarsilva.projecteuler.java;

import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.josimarsilva.projecteuler.java.LargestPrimeFactor.largestPrimeFactorOf;
import static org.assertj.core.api.Assertions.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LargestPrimeFactorTest {

    @ParameterizedTest
    @MethodSource("samples")
    public void shouldReturnTheLargestPrimeFactorOfGivenNumber(long number, long expectedLargestPrimeFactor) {
        assertThat(largestPrimeFactorOf(number)).isEqualTo(expectedLargestPrimeFactor);
    }

    private Stream<Arguments> samples() {
        return Stream.of(
                Arguments.of(13195L, 29),
                Arguments.of(600_851_475_143L, 6857)
        );
    }

}
