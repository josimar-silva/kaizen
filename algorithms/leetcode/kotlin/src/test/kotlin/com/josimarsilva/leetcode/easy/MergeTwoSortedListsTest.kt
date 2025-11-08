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
class MergeTwoSortedListsTest {

    @ParameterizedTest
    @MethodSource("samples")
    fun `should return the unique triplets`(list1: IntArray, list2: IntArray, expectedList: IntArray) {
        val mergedList = mergeTwoLists(list1.toListNode(), list2.toListNode())

        assertThat(mergedList.asIntArray()).isEqualTo(expectedList)
    }

    @ParameterizedTest
    @MethodSource("samples")
    fun `recursive implementation should return the unique triplets`(list1: IntArray, list2: IntArray, expectedList: IntArray) {
        val mergedList = mergeTwoLists(list1.toListNode(), list2.toListNode())

        assertThat(mergedList.asIntArray()).isEqualTo(expectedList)
    }

    private fun samples(): Stream<Arguments> = Stream.of(
        Arguments.of(intArrayOf(1,2,4), intArrayOf(1,3,4), intArrayOf(1,1,2,3,4,4)),
        Arguments.of(intArrayOf(), intArrayOf(), intArrayOf()),
        Arguments.of(intArrayOf(), intArrayOf(0), intArrayOf(0)),
    )
}
