package com.josimarsilva.projecteuler.java;

import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class EvenFibonacciNumbersTest {

    @ParameterizedTest
    @MethodSource("samples")
    public void shouldReturnTheSumUpToOfEvenValueTerms(long limit, long expectedSum) {
        assertThat(EvenFibonacciNumbers.find().sumUpTo(limit)).isEqualTo(expectedSum);
    }

    private Stream<Arguments> samples() {
        return Stream.of(
                Arguments.of(1, 0),
                Arguments.of(10, 10),
                Arguments.of(4_000_000L, 4613732)
        );
    }

}
