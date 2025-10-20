package com.josimarsilva.leetcode.easy

/**
 * You are given an array prices where prices[i] is the price of a given stock on the ith day.
 *
 * You want to maximize your profit by choosing a single day to buy one stock and choosing a different day in the future to sell that stock.
 *
 * Return the maximum profit you can achieve from this transaction. If you cannot achieve any profit, return 0.
 *
 * Constraints:
 *
 * - 1 <= prices.length <= 105
 * - 0 <= prices[i] <= 104
 *
 *
 * Ref.: https://leetcode.com/problems/best-time-to-buy-and-sell-stock
 *
 */
//Time: O(N) / Space: O(1)
fun maxProfit(prices: IntArray): Int {
    if(prices.isEmpty()) return 0

    var lowestPrice = prices[0]
    var maxProfit = 0

    for (price in prices) {
        if (price < lowestPrice) { lowestPrice = price }

        val currentProfit = price - lowestPrice
        if (currentProfit > maxProfit) {
            maxProfit = currentProfit
        }
    }

    return maxProfit
}
