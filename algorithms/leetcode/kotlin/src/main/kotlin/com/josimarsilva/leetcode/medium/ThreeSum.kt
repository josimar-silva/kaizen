package com.josimarsilva.leetcode.medium

/**
 * Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]]
 * such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.
 *
 * Notice that the solution set must not contain duplicate triplets.
 *
 * Constraints:
 * - 3 <= nums.length <= 3000
 * - -105 <= nums[i] <= 105
 *
 * Ref.: https://leetcode.com/problems/3sum
 *
 */
//Time: O(N^2) Space: O(1)
fun threeSum(numbers: IntArray): List<List<Int>> {
    val sortedNumbers = numbers.sorted()
    val result = mutableListOf<List<Int>>()
    val endIndex = numbers.size - 1

    for ((index, x) in sortedNumbers.withIndex()) {
        val previous = index - 1
        if (index > 0 && sortedNumbers[index] == sortedNumbers[previous]) {
            continue
        }

        var leftPointer = index + 1
        var rightPointer = endIndex

        while (leftPointer < rightPointer) {
            var y = sortedNumbers[leftPointer]
            var z = sortedNumbers[rightPointer]

            var threeSum = x + y + z

            if (threeSum == 0) {
                result.add(listOf(x, y, z))
                leftPointer++
                rightPointer--

                while (leftPointer < rightPointer && sortedNumbers[leftPointer] == sortedNumbers[leftPointer - 1]) {
                    leftPointer++
                }

                while (rightPointer > leftPointer && sortedNumbers[rightPointer] == sortedNumbers[rightPointer + 1]) {
                    rightPointer--
                }

                continue
            }

            if (threeSum < 0) {
                leftPointer++
            }
            if (threeSum > 0) {
                rightPointer--
            }
        }
    }

    return result
}