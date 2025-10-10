package com.josimarsilva.leetcode.medium

/**
 * You are given an integer array height of length n.
 * There are n vertical lines drawn such that the two endpoints of the ith line are (i, 0) and (i, height[i]).
 *
 * Find two lines that together with the x-axis form a container, such that the container contains the most water.
 *
 * Return the maximum amount of water a container can store.
 *
 * Notice that you may not slant the container.
 *
 * Constraints:
 * - n == height.length
 * - 2 <= n <= 105
 * - 0 <= height[i] <= 104
 *
 * Ref.: https://leetcode.com/problems/container-with-most-water
 *
 */
/* Time: O(N) Space: O(1) */
fun maxArea(height: IntArray): Int {
    var maxArea = 0
    var leftPointer = 0
    var rightPoint = height.size - 1

    while (leftPointer < rightPoint) {
        val leftSide = height[leftPointer]
        val rightSide = height[rightPoint]
        val width = rightPoint - leftPointer
        val currentArea = width * minOf(leftSide, rightSide)

        maxArea = maxOf(currentArea, maxArea)

        if(leftSide < rightSide) {
            leftPointer++
        } else {
            rightPoint--
        }
    }

    return maxArea
}
