package com.josimarsilva.leetcode.easy

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BinarySearchTest {

    @ParameterizedTest
    @MethodSource("samples")
    fun `should return the index of the target`(numbers: IntArray, target: Int, expectedIndex: Int) {
        assertThat(search(numbers, target)).isEqualTo(expectedIndex)
    }

    private fun samples(): Stream<Arguments> = Stream.of(
        Arguments.of(intArrayOf(-1, 0, 3, 5, 9, 12), 9, 4),
        Arguments.of(intArrayOf(-2, 0, 3, 5, 9, 12), 0, 1),
        Arguments.of(intArrayOf(-1, 0, 3, 5, 9, 12), 2, -1),
        Arguments.of(intArrayOf(), 1, -1),
        Arguments.of(intArrayOf(2), 2, 0),
        Arguments.of(intArrayOf(1), 3, -1),
    )

}
