package com.josimarsilva.projecteuler.java;

import com.josimarsilva.projecteuler.java.common.Range;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SmallestMultipleTest {

    @ParameterizedTest
    @MethodSource("samples")
    public void shouldReturnTheSmallestMultipleFromGivenRange(Range range, long expectedMultiple) {
        assertThat(SmallestMultiple.find().smallestMultipleWithin(range)).isEqualTo(expectedMultiple);
    }

    private Stream<Arguments> samples() {
        return Stream.of(
                Arguments.of(Range.from(1, 10), 2520L),
                Arguments.of(Range.from(1, 20), 232792560L)
        );
    }
}
