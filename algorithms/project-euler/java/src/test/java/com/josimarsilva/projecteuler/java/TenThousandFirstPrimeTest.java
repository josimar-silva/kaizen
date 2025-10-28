package com.josimarsilva.projecteuler.java;

import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TenThousandFirstPrimeTest {

    @ParameterizedTest
    @MethodSource("samples")
    public void shouldReturnThePrimeNumberAtGivenPosition(long position, long expectedPrime) {
        assertThat(TenThousandFirstPrime.at(position).calculate()).isEqualTo(expectedPrime);
    }

    private Stream<Arguments> samples() {
        return Stream.of(
                Arguments.of(6L, 13L),
                Arguments.of(10L, 29L),
                Arguments.of(10_001L, 104743L)
        );
    }

}
