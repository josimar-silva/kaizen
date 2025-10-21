package com.josimarsilva.leetcode.easy;

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MajorityElementTest {

    @ParameterizedTest
    @MethodSource("samples")
    fun `should return the majority element of a given array`(elements: IntArray, majorityElement: Int) {
        assertThat(majorityElement(elements)).isEqualTo(majorityElement)
        assertThat(majorityElementNaive(elements)).isEqualTo(majorityElement)
    }

    private fun samples(): Stream<Arguments> = Stream.of(
        Arguments.of(intArrayOf(3, 2, 3), 3),
        Arguments.of(intArrayOf(2, 2, 1, 1, 1, 2, 2), 2)
    )

}
