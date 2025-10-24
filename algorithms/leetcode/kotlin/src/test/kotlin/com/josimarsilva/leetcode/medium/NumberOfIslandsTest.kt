package com.josimarsilva.leetcode.medium

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class NumberOfIslandsTest {

    @ParameterizedTest
    @MethodSource("samples")
    fun `should return the number of islands using BFS`(grid: Array<CharArray>, expectedIslands: Int) {
        Assertions.assertThat(numIslandsUsingBFS(grid)).isEqualTo(expectedIslands)
    }

    @ParameterizedTest
    @MethodSource("samples")
    fun `should return the number of islands using DFS`(grid: Array<CharArray>, expectedIslands: Int) {
        Assertions.assertThat(numIslandsUsingDFS(grid)).isEqualTo(expectedIslands)
    }

    private fun samples(): Stream<Arguments> = Stream.of(
        Arguments.of(
            arrayOf(
                charArrayOf('1', '1', '1', '1', '0'),
                charArrayOf('1', '1', '0', '1', '0'),
                charArrayOf('1', '1', '0', '0', '0'),
                charArrayOf('0', '0', '0', '0', '0')
            ), 1
        ),
        Arguments.of(
            arrayOf(
                charArrayOf('1', '1', '0', '0', '0'),
                charArrayOf('1', '1', '0', '0', '0'),
                charArrayOf('0', '0', '1', '0', '0'),
                charArrayOf('0', '0', '0', '1', '1')
            ), 3
        ),
    )

}
