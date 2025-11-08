package com.josimarsilva.leetcode.common

fun ListNode?.asIntArray(): IntArray {
    val list = mutableListOf<Int>()
    var currentNode = this
    while (currentNode != null) {
        list.add(currentNode.`val`)
        currentNode = currentNode.next
    }
    return list.toIntArray()
}

fun IntArray.toListNode(): ListNode? {
    if (this.isEmpty()) return null
    val head = ListNode(this[0])
    var currentNode = head
    for (i in 1 until this.size) {
        val newNode = ListNode(this[i])
        currentNode.next = newNode
        currentNode = newNode
    }
    return head
}