package com.josimarsilva.leetcode.easy

/**
 *
 * Given an integer array nums, return true if any value appears at least twice in the array,
 * and return false if every element is distinct.
 *
 * Constraints:
 * - 1 <= nums.length <= 105
 * - -109 <= nums[i] <= 109
 *
 *
 * Ref.: https://leetcode.com/problems/contains-duplicate
 */
//Time: O(N log N) Space: O(1)
fun containsDuplicate(numbers: IntArray): Boolean {
    if (numbers.isEmpty()) return false
    if (numbers.size == 1) return false

    val orderedNumbers = numbers.sorted()

    for ((index, number) in orderedNumbers.withIndex()) {
        val nextIndex = index + 1
        if (nextIndex < numbers.size && number == orderedNumbers[nextIndex]) return true
    }

    return false
}

//Time: O(N) Space: O(N)
fun containsDuplicateNaive(numbers: IntArray): Boolean {
    if (numbers.isEmpty()) return false
    if (numbers.size == 1) return false

    val memo = mutableSetOf<Int>()

    for (number in numbers) {
        if (memo.contains(number)) {
            return true
        } else {
            memo.add(number)
        }
    }

    return false
}

