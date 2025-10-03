package com.josimarsilva.leetcode.medium

import com.josimarsilva.leetcode.common.arrayToListNode
import com.josimarsilva.leetcode.common.asIntArray
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SwapNodesInParisTest {

    @ParameterizedTest
    @MethodSource("samples")
    fun `should swap pairs`(listNode: IntArray, expected: IntArray) {
        val result = SwapNodesInPairs().swapPairs(arrayToListNode(listNode))

        assertThat(asIntArray(result)).isEqualTo(expected)
    }

    private fun samples(): Stream<Arguments> = Stream.of(
        Arguments.of(intArrayOf(1, 2, 3, 4), intArrayOf(2, 1, 4, 3)),
        Arguments.of(intArrayOf(1), intArrayOf(1)),
        Arguments.of(intArrayOf(), intArrayOf()),
        Arguments.of(intArrayOf(1, 2, 3), intArrayOf(2, 1, 3))
    )

}