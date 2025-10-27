package com.josimarsilva.leetcode.easy

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class RemoveElementTest {

    @ParameterizedTest
    @MethodSource("samples")
    fun `should remove element from array`(numbers: IntArray, toBeRemoved: Int, expectedResult: Int) {
        assertThat(removeElement(numbers, toBeRemoved)).isEqualTo(expectedResult)
    }

    @ParameterizedTest
    @MethodSource("samples")
    fun `should remove element from array using naive implementation`(numbers: IntArray, toBeRemoved: Int, expectedResult: Int) {
        assertThat(removeElementNaive(numbers, toBeRemoved)).isEqualTo(expectedResult)
    }

    private fun samples(): Stream<Arguments> = Stream.of(
        Arguments.of(intArrayOf(3, 2, 2, 3), 3, 2),
        Arguments.of(intArrayOf(0, 1, 2, 2, 3, 0, 4, 2), 2, 5),
        Arguments.of(intArrayOf(), 3, 0),
        Arguments.of(intArrayOf(1, 1, 1, 1), 1, 0),
        Arguments.of(intArrayOf(2, 2, 2, 2), 1, 4),
    )

}
