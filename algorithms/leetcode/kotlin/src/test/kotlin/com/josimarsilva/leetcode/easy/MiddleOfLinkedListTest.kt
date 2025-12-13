package com.josimarsilva.leetcode.easy

import com.josimarsilva.leetcode.common.asIntArray
import com.josimarsilva.leetcode.common.toListNode
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MiddleOfLinkedListTest {

    @ParameterizedTest
    @MethodSource("samples")
    fun `should return the middle of a given linked list`(head: IntArray, expectedResult: IntArray) {
        assertThat(middleNode(head.toListNode()).asIntArray()).isEqualTo(expectedResult)
    }

    private fun samples(): Stream<Arguments> = Stream.of(
        Arguments.of(intArrayOf(1, 2, 3, 4, 5), intArrayOf(3, 4, 5)),
        Arguments.of(intArrayOf(1, 2, 3, 4, 5, 6), intArrayOf(4, 5, 6)),
        Arguments.of(intArrayOf(1, 2), intArrayOf(2)),
        Arguments.of(intArrayOf(1), intArrayOf(1)),
        Arguments.of(intArrayOf(), intArrayOf()),
    )

}
