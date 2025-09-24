package com.josimarsilva.leetcode.medium

/**
 *
 * You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order, and each of their nodes contains a single digit. Add the two numbers and return the sum as a linked list.
 *
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 *
 * Constraints:
 *
 *  - The number of nodes in each linked list is in the range [1, 100].
 *  - 0 <= Node.val <= 9
 *  - It is guaranteed that the list represents a number that does not have leading zeros.
 *
 * Example:
 * var li = ListNode(5)
 * var v = li.`val`
 * Definition for singly-linked list.
 * class ListNode(var `val`: Int) {
 *     var next: ListNode? = null
 * }
 *
 * Ref.: https://leetcode.com/problems/add-two-numbers/
 */
class TwoNumbers {
    fun addTwoNumbers(l1: ListNode?, l2: ListNode?): ListNode? {
        val dummyHead = ListNode(0)
        var current = dummyHead

        var parent1 = l1
        var parent2 = l2
        var carry = 0

        while (parent1 != null || parent2 != null) {
            val x = parent1?.`val` ?: 0
            val y = parent2?.`val` ?: 0
            val sum = x + y + carry

            carry = sum / 10

            current.next = ListNode(sum % 10)
            current = current.next!!

            parent1 = parent1?.next
            parent2 = parent2?.next
        }

        if (carry > 0) {
            current.next = ListNode(carry)
        }

        return dummyHead.next
    }
}

class ListNode(var `val`: Int) {
    var next: ListNode? = null
}
