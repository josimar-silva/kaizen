package com.josimarsilva.leetcode.medium


import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TwoNumbersTest {

    @ParameterizedTest
    @MethodSource("samples")
    fun `should add two numbers and return the sum as a linked list`(l1: IntArray, l2: IntArray, expected: IntArray) {
        val l1Node = arrayToListNode(l1)
        val l2Node = arrayToListNode(l2)

        val result = TwoNumbers().addTwoNumbers(l1Node, l2Node)

        assertThat(asIntArray(result)).isEqualTo(expected)
    }

    private fun samples(): Stream<Arguments> = Stream.of(
        Arguments.of(intArrayOf(2, 4, 3), intArrayOf(5, 6, 4), intArrayOf(7, 0, 8)),
        Arguments.of(intArrayOf(0), intArrayOf(0), intArrayOf(0)),
        Arguments.of(intArrayOf(9, 9, 9, 9, 9, 9, 9), intArrayOf(9, 9, 9, 9), intArrayOf(8, 9, 9, 9, 0, 0, 0, 1))
    )

    private fun asIntArray(listNode: ListNode?): IntArray {
        val list = mutableListOf<Int>()
        var currentNode = listNode
        while (currentNode != null) {
            list.add(currentNode.`val`)
            currentNode = currentNode.next
        }
        return list.toIntArray()
    }

    private fun arrayToListNode(array: IntArray): ListNode? {
        if (array.isEmpty()) return null
        val head = ListNode(array[0])
        var currentNode = head
        for (i in 1 until array.size) {
            val newNode = ListNode(array[i])
            currentNode.next = newNode
            currentNode = newNode
        }
        return head
    }
}
