package com.josimarsilva.leetcode.easy

import java.util.stream.Stream
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TwoSumTest {

    @ParameterizedTest
    @MethodSource("samples")
    fun `should return indexes for the integers`(numbers: IntArray, target: Int, expectedIndexes: IntArray) {
        assertThat(TwoSum().twoSum(numbers, target)).isEqualTo(expectedIndexes)
    }

    private fun samples(): Stream<Arguments> = Stream.of(
        Arguments.of(intArrayOf(2,7,11,15), 9, intArrayOf(0,1)),
        Arguments.of(intArrayOf(3,2,4), 6, intArrayOf(1,2)),
        Arguments.of(intArrayOf(3,3), 6, intArrayOf(0,1)),
        Arguments.of(intArrayOf(-1, -2, -3, -4, -5), -8, intArrayOf(2, 4)),
        Arguments.of(intArrayOf(0, 4, 3, 0), 0, intArrayOf(0, 3)),
        Arguments.of(intArrayOf(-3, 4, 3, 90), 0, intArrayOf(0, 2)),
        Arguments.of(intArrayOf(100, 200, 300, 400), 700, intArrayOf(2, 3)),
    )


}
