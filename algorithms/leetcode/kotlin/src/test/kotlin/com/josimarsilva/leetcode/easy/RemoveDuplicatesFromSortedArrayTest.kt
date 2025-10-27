package com.josimarsilva.leetcode.easy;

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class RemoveDuplicatesFromSortedArrayTest {

    @ParameterizedTest
    @MethodSource("samples")
    fun `should remove duplicates from sorted array`(numbers: IntArray, expectedResult: Int) {
        assertThat(removeDuplicates(numbers)).isEqualTo(expectedResult)
    }

    private fun samples(): Stream<Arguments> = Stream.of(
        Arguments.of(intArrayOf(1, 1, 2), 2),
        Arguments.of(intArrayOf(0, 0, 1, 2, 2, 3, 4), 5),
        Arguments.of(intArrayOf(), 0),
        Arguments.of(intArrayOf(1, 1, 1, 1), 1),
        Arguments.of(intArrayOf(1, 2, 3, 4), 4),
    )

}
