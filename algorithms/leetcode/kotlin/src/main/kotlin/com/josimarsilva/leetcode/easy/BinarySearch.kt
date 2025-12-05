package com.josimarsilva.leetcode.easy

/**
 * Given an array of integers nums which is sorted in ascending order, and an integer target, write a function to search target in nums.
 * If target exists, then return its index. Otherwise, return -1.
 *
 * You must write an algorithm with O(log n) runtime complexity.
 *
 * Constraints:
 * - 1 <= nums.length <= 104
 * - -104 < nums[i], target < 104
 * - All the integers in nums are unique.
 * - nums is sorted in ascending order.
 *
 * Ref.: https://leetcode.com/problems/binary-search
 */
fun search(numbersOrdered: IntArray, target: Int): Int {
    if (numbersOrdered.isEmpty()) return -1

    var left = 0
    var right = numbersOrdered.size - 1

    while (left <= right) {
        val mid = left + (right - left) / 2
        val possibleTarget = numbersOrdered[mid]

        if (possibleTarget < target) {
            left = mid + 1
        }

        if (possibleTarget > target) {
            right = mid - 1
        }

        if (possibleTarget == target) {
            return mid
        }
    }

    return -1
}
