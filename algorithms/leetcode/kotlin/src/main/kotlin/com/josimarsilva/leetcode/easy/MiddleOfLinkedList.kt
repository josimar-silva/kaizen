package com.josimarsilva.leetcode.easy

import com.josimarsilva.leetcode.common.ListNode

/**
 * Given the head of a singly linked list, return the middle node of the linked list.
 *
 * If there are two middle nodes, return the second middle node.
 *
 * Constraints:
 * - The number of nodes in the list is in the range [1, 100].
 * - 1 <= Node.val <= 100
 *
 * Ref.: https://leetcode.com/problems/middle-of-the-linked-list
 *
 */
//Time: O(N) Space: (1)
fun middleNode(head: ListNode?): ListNode? {
    if (head == null || head.next == null) return head

    var slow = head
    var fast = head.next

    while (fast != null) {
        slow = slow?.next
        fast = fast.next?.next
    }

    return slow
}