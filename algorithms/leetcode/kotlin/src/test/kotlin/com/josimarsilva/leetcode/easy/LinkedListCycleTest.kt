package com.josimarsilva.leetcode.easy

import com.josimarsilva.leetcode.common.arrayToListNode
import com.josimarsilva.leetcode.medium.ListNode
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class LinkedListCycleTest {

    @ParameterizedTest
    @MethodSource("cyclicSamples")
    fun `should return true given cyclic list`(listNode: IntArray, pos: Int) {
        val head = createCyclicListNode(listNode, pos)
        assertThat(hasCycleNaive(head)).isTrue
        assertThat(hasCycle(head)).isTrue
    }

    private fun cyclicSamples(): Stream<Arguments> = Stream.of(
        Arguments.of(intArrayOf(3, 2, 0, -4), 1),
        Arguments.of(intArrayOf(1, 2), 0),
        Arguments.of(intArrayOf(1), 0),
    )

    @ParameterizedTest
    @MethodSource("nonCyclicSamples")
    fun `should return false given non-cyclic list`(listNode: IntArray) {
        val head = arrayToListNode(listNode)
        assertThat(hasCycleNaive(head)).isFalse
        assertThat(hasCycle(head)).isFalse
    }

    private fun nonCyclicSamples(): Stream<Arguments> = Stream.of(
        Arguments.of(intArrayOf(1)),
        Arguments.of(intArrayOf(1, 2)),
    )

    private fun createCyclicListNode(nodes: IntArray, pos: Int): ListNode? {
        val head = arrayToListNode(nodes)
        if (pos < 0) {
            return head
        }
        var tail = head
        while (tail?.next != null) {
            tail = tail.next
        }
        var cycleNode = head
        for (i in 0 until pos) {
            cycleNode = cycleNode?.next
        }
        tail?.next = cycleNode
        return head
    }
}