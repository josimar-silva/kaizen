package com.josimarsilva.leetcode.medium

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ThreeSumTest {

    @ParameterizedTest
    @MethodSource("samples")
    fun `should return the unique triplets`(numbers: IntArray, expectedTriplets: List<List<Int>>) {
        assertThat(threeSum(numbers)).isEqualTo(expectedTriplets)
    }

    private fun samples(): Stream<Arguments> = Stream.of(
        Arguments.of(intArrayOf(-1, 0, 1, 2, -1, -4), listOf<List<Int>>(listOf(-1, -1, 2), listOf<Int>(-1, 0, 1))),
        Arguments.of(intArrayOf(0, 1, 1), emptyList<List<Int>>()),
        Arguments.of(intArrayOf(0, 0, 0), listOf<List<Int>>(listOf<Int>(0, 0, 0)))
    )
}