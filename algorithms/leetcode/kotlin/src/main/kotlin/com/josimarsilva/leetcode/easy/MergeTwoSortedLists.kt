package com.josimarsilva.leetcode.easy

import com.josimarsilva.leetcode.medium.ListNode

/**
 *
 * You are given the heads of two sorted linked lists list1 and list2.
 *
 * Merge the two lists into one sorted list. The list should be made by splicing together
 * the nodes of the first two lists.
 *
 * Return the head of the merged linked list.
 *
 * Constraints:
 *  - The number of nodes in both lists is in the range [0, 50].
 * - -100 <= Node.val <= 100
 * - Both list1 and list2 are sorted in non-decreasing order.
 *
 * Ref.: https://leetcode.com/problems/merge-two-sorted-lists
 */
//Time O(N+M) / Space O(1)
fun mergeTwoLists(list1: ListNode?, list2: ListNode?): ListNode? {
    if (list1 == null && list2 == null) {
        return list1
    }
    if (list1 == null) return list2
    if (list2 == null) return list1

    val dummyHead = ListNode(0)
    var l1 = list1
    var l2 = list2

    var tail: ListNode? = dummyHead

    while (l1 != null && l2 != null) {
        if (l1.`val` < l2.`val`) {
            tail?.next = l1
            tail = tail?.next
            l1 = l1.next
        } else {
            tail?.next = l2
            tail = tail?.next
            l2 = l2.next
        }
    }

    tail?.next = l1 ?: l2

    return dummyHead.next
}


//Time O(N+M) / Space O(N+M)
fun mergeTwoListsRecursive(list1: ListNode?, list2: ListNode?): ListNode? {
    if (list1 == null && list2 == null) {
        return list1
    }
    if (list1 == null) return list2
    if (list2 == null) return list1

    fun mergeSort(a: ListNode, b: ListNode): ListNode {
        val newHead = a
        newHead.next = mergeTwoListsRecursive(a.next, b)
        return newHead
    }

    return if (list1.`val` < list2.`val`) {
        mergeSort(list1, list2)
    } else {
        mergeSort(list2, list1)
    }
}
