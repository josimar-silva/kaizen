package com.josimarsilva.leetcode.easy

import com.josimarsilva.leetcode.medium.ListNode

/**
 * Given head, the head of a linked list, determine if the linked list has a cycle in it.
 *
 * There is a cycle in a linked list if there is some node in the list that can be reached again by continuously following the next pointer. Internally, pos is used to denote the index of the node that tail's next pointer is connected to. Note that pos is not passed as a parameter.
 *
 * Return true if there is a cycle in the linked list. Otherwise, return false.
 *
 * Constraints:
 *
 * - The number of the nodes in the list is in the range [0, 104].
 * - -105 <= Node.val <= 105
 * - pos is -1 or a valid index in the linked-list.
 *
 * Ref.: https://leetcode.com/problems/linked-list-cycle
 */
//Time: O(N) / Space: O(1)
fun hasCycle(head: ListNode?): Boolean {
    if (head?.next == null) return false
    if (head.next == head) return true

    var slow = head
    var fast = head.next

    while (fast != null) {
        if (fast === slow) return true
        slow = slow?.next
        fast = fast.next?.next
    }

    return false
}


//Time: O(N) / Space: O(N)
fun hasCycleNaive(head: ListNode?): Boolean {
    if (head?.next == null) return false
    if (head.next == head) return true

    var node = head
    var memo = mutableSetOf<ListNode>()

    while (node != null) {
        if (memo.contains(node)) {
            return true
        }

        memo.add(node)

        node = node.next
    }

    return false
}