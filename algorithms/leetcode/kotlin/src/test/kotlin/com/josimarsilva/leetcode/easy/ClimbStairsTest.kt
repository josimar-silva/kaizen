package com.josimarsilva.leetcode.easy

import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream
import org.assertj.core.api.Assertions.assertThat

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ClimbStairsTest {

    @ParameterizedTest
    @MethodSource("samples")
    fun `should return the number of ways to climb the stairs`(steps: Int, expectedNumberOfWays: Int) {
        assertThat(ClimbStairs().climbStairs(steps)).isEqualTo(expectedNumberOfWays)
        assertThat(ClimbStairs().climbStairsWithMemoization(steps)).isEqualTo(expectedNumberOfWays)
        assertThat(climbStairsWithMath(steps)).isEqualTo(expectedNumberOfWays)
    }

    private fun samples(): Stream<Arguments> = Stream.of(
        Arguments.of(2, 2), //1. 1 step + 1 step | 2. 2 steps
        Arguments.of(3, 3), //1. 1 step + 1 step + 1 step | 2. 1 step + 2 steps | 3. 2 steps + 1 step
        Arguments.of(45, 1836311903),
    )
}