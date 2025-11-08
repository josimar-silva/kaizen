package com.josimarsilva.leetcode.easy

import com.josimarsilva.leetcode.common.ListNode

/**
 *
 * Given the head of a singly linked list, reverse the list, and return the reversed list.
 *
 * Constraints:
 * - The number of nodes in the list is the range [0, 5000].
 * - -5000 <= Node.val <= 5000
 *
 * Ref.: https://leetcode.com/problems/reverse-linked-list
 */
//Time: O(N) Space: O(1)
fun reverseList(head: ListNode?): ListNode? {
    if (head == null || head.next == null) return head

    var previousNode: ListNode? = null
    var currentNode = head

    while (currentNode != null) {
        val nextNode = currentNode.next

        currentNode.next = previousNode
        previousNode = currentNode
        currentNode = nextNode
    }

    return previousNode
}
