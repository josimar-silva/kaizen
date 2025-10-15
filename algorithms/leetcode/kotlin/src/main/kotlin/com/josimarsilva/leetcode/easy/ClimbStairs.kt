package com.josimarsilva.leetcode.easy

import kotlin.math.pow
import kotlin.math.roundToInt
import kotlin.math.sqrt

/**
 *
 * You are climbing a staircase. It takes n steps to reach the top.
 *
 * Each time you can either climb 1 or 2 steps.
 * In how many distinct ways can you climb to the top?
 *
 * Constraints:
 * - 1 <= n <= 45
 *
 * Ref.: https://leetcode.com/problems/climbing-stairs
 */
class ClimbStairs() {
    val memoization = mutableMapOf<Int, Int>()

    //Time: O(N), Space: O(N)
    fun climbStairsWithMemoization(steps: Int): Int {
        if (steps == 1) return 1
        if (steps == 2) return 2

        return memoization.getOrPut(steps) { climbStairsWithMemoization(steps - 1) + climbStairsWithMemoization(steps - 2) }
    }

    //Time: O(N), Space: O(1)
    fun climbStairs(steps: Int): Int {
        if (steps == 1) return 1
        if (steps == 2) return 2

        var oneStepBehind = 1
        var twoStepsBehind = 1

        (2..steps).forEach { i ->
            val current = oneStepBehind + twoStepsBehind
            twoStepsBehind = oneStepBehind
            oneStepBehind = current
        }

        return oneStepBehind
    }
}

/**
 *
 * Implementation using the Binet's formula:
 *  F(k) = (φ^k - ψ^k) / √5
 *
 *  Where:
 *    - k is the Fibonacci number index, so for us, it will be n + 1.
 *    - √5 is the square root of 5.
 *    - φ (phi) is the golden ratio: (1 + √5) / 2
 *    - ψ (psi) is its conjugate: (1 - √5) / 2
 *
 *  Time: O(1) / Space: O(1)
 */
fun climbStairsWithMath(steps: Int): Int {
    val k = steps + 1
    val squareRootOfFive = sqrt(5.0)
    val phi = (1 + squareRootOfFive) / 2
    val psi = (1 - squareRootOfFive) / 2

    return ((phi.pow(k) - psi.pow(k)) / squareRootOfFive).roundToInt()
}