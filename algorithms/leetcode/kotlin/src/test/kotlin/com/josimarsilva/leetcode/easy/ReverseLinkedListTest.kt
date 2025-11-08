package com.josimarsilva.leetcode.easy;

import com.josimarsilva.leetcode.common.toListNode
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ReverseLinkedListTest {

    @ParameterizedTest
    @MethodSource("samples")
    fun `should reverse a given linked list`(head: IntArray, expectedResult: IntArray) {
        assertThat(reverseList(head.toListNode())).isEqualTo(expectedResult.toListNode())
    }

    private fun samples(): Stream<Arguments> = Stream.of(
        Arguments.of(intArrayOf(1,2,3,4,5), intArrayOf(5,4,3,2,1)),
        Arguments.of(intArrayOf(1,2), intArrayOf(2,1)),
        Arguments.of(intArrayOf(1), intArrayOf(1)),
        Arguments.of(intArrayOf(), intArrayOf()),
    )

}
