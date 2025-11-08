package com.josimarsilva.leetcode.easy

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ContainsDuplicateTest {

    @ParameterizedTest
    @MethodSource("samplesWithDuplicates")
    fun `should return true if array contains duplicates`(numbers: IntArray) {
        assertThat(containsDuplicateNaive(numbers)).isTrue
        assertThat(containsDuplicate(numbers)).isTrue
    }

    private fun samplesWithDuplicates(): Stream<Arguments> = Stream.of(
        Arguments.of(intArrayOf(1, 2, 3, 1)),
        Arguments.of(intArrayOf(1, 2, 1, 4)),
        Arguments.of(intArrayOf(1, 1, 1, 3, 3, 4, 3, 2, 4, 2)),
    )

    @ParameterizedTest
    @MethodSource("samplesWithoutDuplicates")
    fun `should return false if array does not contains duplicates`(numbers: IntArray) {
        assertThat(containsDuplicateNaive(numbers)).isFalse
        assertThat(containsDuplicate(numbers)).isFalse
    }

    private fun samplesWithoutDuplicates(): Stream<Arguments> = Stream.of(
        Arguments.of(intArrayOf(1, 2, 3, 4)),
        Arguments.of(intArrayOf(1)),
        Arguments.of(intArrayOf()),
    )


}
