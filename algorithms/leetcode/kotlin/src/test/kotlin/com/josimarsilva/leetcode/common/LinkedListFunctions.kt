package com.josimarsilva.leetcode.common

import com.josimarsilva.leetcode.medium.ListNode

fun asIntArray(listNode: ListNode?): IntArray {
    val list = mutableListOf<Int>()
    var currentNode = listNode
    while (currentNode != null) {
        list.add(currentNode.`val`)
        currentNode = currentNode.next
    }
    return list.toIntArray()
}

fun arrayToListNode(array: IntArray): ListNode? {
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