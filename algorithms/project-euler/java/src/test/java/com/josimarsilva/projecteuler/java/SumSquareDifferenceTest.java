package com.josimarsilva.projecteuler.java;

import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SumSquareDifferenceTest {

    @ParameterizedTest
    @MethodSource("samples")
    public void shouldReturnTheSumOfSquareDifferenceUsingFastImplementation(long limit, long expectedSum) {
        assertThat(SumSquareDifference.of(limit).calculate()).isEqualTo(expectedSum);
    }

    @ParameterizedTest
    @MethodSource("samples")
    public void shouldReturnTheSumOfSquareDifferenceUsingNaiveImplementation(long limit, long expectedSum) {
        assertThat(SumSquareDifference.of(limit).calculateNaive()).isEqualTo(expectedSum);
    }

    private Stream<Arguments> samples() {
        return Stream.of(
                Arguments.of(1L, 0L),
                Arguments.of(10L, 2640L),
                Arguments.of(100L, 25164150L)
        );
    }

}
