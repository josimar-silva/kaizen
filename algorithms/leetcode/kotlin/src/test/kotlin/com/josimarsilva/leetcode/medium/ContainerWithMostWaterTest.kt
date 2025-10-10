package com.josimarsilva.leetcode.medium

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ContainerWithMostWaterTest {

    @ParameterizedTest
    @MethodSource("samples")
    fun `should return maximum amount of water a container can store`(buckets: IntArray, expectedCapacity: Int) {
        assertThat(maxArea(buckets)).isEqualTo(expectedCapacity)
    }

    private fun samples(): Stream<Arguments> = Stream.of(
        Arguments.of(intArrayOf(1,8,6,2,5,4,8,3,7), 49),
        Arguments.of(intArrayOf(1,1), 1),
        Arguments.of(intArrayOf(5, 4, 3, 2, 1), 6)
    )

}