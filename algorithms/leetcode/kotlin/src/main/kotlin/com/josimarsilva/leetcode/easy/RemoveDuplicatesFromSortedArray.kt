package com.josimarsilva.leetcode.easy

/**
 * Given an integer array nums sorted in non-decreasing order,
 * remove the duplicates in-place such that each unique element appears only once.
 * The relative order of the elements should be kept the same.
 *
 * Consider the number of unique elements in nums to be k.
 * After removing duplicates, return the number of unique elements k.
 *
 * The first k elements of nums should contain the unique numbers in sorted order.
 * The remaining elements beyond index k - 1 can be ignored.
 *
 * Constraints:
 *
 * - 1 <= nums.length <= 3 * 104
 * - -100 <= nums[i] <= 100
 * - nums is sorted in non-decreasing order.
 *
 * Ref.: https://leetcode.com/problems/remove-duplicates-from-sorted-array
 */
//Time: O(N) Space: O(1)
fun removeDuplicates(numbers: IntArray): Int {
    if (numbers.isEmpty()) return 0

    var slowPointer = 0
    numbers.forEachIndexed { index, number ->
        if (number != numbers[slowPointer]) {
            slowPointer++
            numbers[slowPointer] = number
        }
    }

    return slowPointer + 1
}
