package com.josimarsilva.leetcode.medium

import com.josimarsilva.leetcode.common.ListNode

/**
 * Given a linked list, swap every two adjacent nodes and return its head.
 * You must solve the problem without modifying the values in the list's nodes
 * (i.e., only nodes themselves may be changed.)
 *
 * Constraints:
 *
 * - The number of nodes in the list is in the range [0, 100].
 * - 0 <= Node.val <= 100
 *
 * Ref.: https://leetcode.com/problems/swap-nodes-in-pairs
 *
 */
class SwapNodesInPairs {

    fun swapPairs(head: ListNode?): ListNode? {
        if (head == null) return null
        if (head.next == null) return head

        val dummyHead = ListNode(0)

        dummyHead.next = head

        var previous = dummyHead

        while (previous.next != null && previous.next?.next != null) {
            val node1 = previous.next
            val node2 = previous.next?.next
            val rest = node2?.next

            node2?.next = node1
            node1?.next = rest
            previous.next = node2

            previous = previous.next!!.next!!
        }

        return dummyHead.next
    }

    /**
     * Time: O(N)
     * Space: O(N)
     */
    fun swapPairsWithRecursion(head: ListNode?): ListNode? {
        if (head == null) return null
        if (head.next == null) return head

        val newHead = head.next
        val rest = newHead?.next

        newHead?.next = head

        head.next = swapPairs(rest)

        return newHead
    }

}