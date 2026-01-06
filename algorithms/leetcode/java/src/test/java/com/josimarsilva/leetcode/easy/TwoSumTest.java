package com.josimarsilva.leetcode.easy;

import java.util.stream.Stream;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TwoSumTest {

    @ParameterizedTest
    @MethodSource("getSamples")
    public void shouldParseBooleanString(int[] numbers, int target, int[] expectedResult) {
        final var twoSum = new TwoSum(numbers, target);

        assertThat(twoSum.calculate()).isEqualTo(expectedResult);
    }

    private static Stream<Arguments> getSamples() {
        return Stream.of(
                Arguments.of(new int[]{2, 7, 11, 15}, 9, new int[]{0, 1}),
                Arguments.of(new int[]{3, 2, 4}, 6, new int[]{1, 2}),
                Arguments.of(new int[]{3, 3}, 6, new int[]{0, 1}),
                Arguments.of(new int[]{}, 8, new int[0])
        );
    }

}
