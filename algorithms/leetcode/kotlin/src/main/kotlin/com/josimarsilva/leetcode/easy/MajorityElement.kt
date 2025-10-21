package com.josimarsilva.leetcode.easy

/**
 * Given an array nums of size n, return the majority element.
 *
 * The majority element is the element that appears more than ⌊n / 2⌋ times.
 * You may assume that the majority element always exists in the array.
 *
 * Constraints:
 * - n == nums.length
 * - 1 <= n <= 5 * 104
 * - -109 <= nums[i] <= 109
 *
 * Ref.: https://leetcode.com/problems/majority-element
 *
 */
// Time: O(N) / Space: O(1)
fun majorityElement(numbers: IntArray): Int {
    var candidate = 0
    var count = 0

    numbers.forEach { number ->
        if (count == 0) candidate = number
        if (candidate == number) count++ else count--
    }

    return candidate
}

//Time: O(N) / Space: O(N)
fun majorityElementNaive(numbers: IntArray) = numbers.groupBy { it }.maxBy { it.value.size }.key
