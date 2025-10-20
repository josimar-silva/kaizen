package com.josimarsilva.leetcode.easy

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BestTimeToBuyAndSellStockTest {

    @ParameterizedTest
    @MethodSource("samples")
    fun `should return the max profit for a given time`(prices: IntArray, expectedResult: Int) {
        assertThat(maxProfit(prices)).isEqualTo(expectedResult)
    }

    private fun samples(): Stream<Arguments> = Stream.of(
        Arguments.of(intArrayOf(7, 1, 5, 3, 6, 4), 5),
        Arguments.of(intArrayOf(7, 6, 4, 3, 1), 0),
        Arguments.of(intArrayOf(), 0),
    )

}
