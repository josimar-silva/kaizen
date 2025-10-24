package com.josimarsilva.leetcode.medium

import java.util.*

/**
 *
 * Given an m x n 2D binary grid 'grid' which represents a map of '1's (land) and '0's (water),
 * return the number of islands.
 *
 * An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
 * You may assume all four edges of the grid are all surrounded by water.
 *
 * Constraints:
 * - m == grid.length
 * - n == grid[i].length
 * - 1 <= m, n <= 300
 * - grid[i][j] is '0' or '1'.
 *
 * Ref.: https://leetcode.com/problems/number-of-islands
 *
 */
fun numIslandsUsingDFS(grid: Array<CharArray>): Int {
    var numberOfIslands = 0

    fun dfs(row: Int, column: Int) {
        if (row < 0 || row >= grid.size || column < 0 || column >= grid[0].size || grid[row][column] == '0') return

        grid[row][column] = '0'

        //Process adjacent positions
        dfs(row, column - 1) // ←
        dfs(row, column + 1) // →
        dfs(row - 1, column) // ↑
        dfs(row + 1, column) // ↓
    }

    for (row in 0..<grid.size) {
        for (column in 0..<grid[0].size) {
            if (grid[row][column] == '1') {
                numberOfIslands++
                dfs(row, column)
            }
        }
    }

    return numberOfIslands
}

//Time: O(N*M) Space: O(min(N,M))
fun numIslandsUsingBFS(grid: Array<CharArray>): Int {
    var numberOfIslands = 0

    fun isLand(row: Int, column: Int): Boolean =
        row >= 0 && row < grid.size && column >= 0 && column < grid[0].size && grid[row][column] == '1'

    fun bfs(row: Int, column: Int) {
        val queue = LinkedList<Pair<Int, Int>>().apply { add(Pair(row, column)) }

        while (queue.isNotEmpty()) {
            val position = queue.poll()
            val currentRow = position.first
            val currentColumn = position.second

            val surroundings = arrayOf(
                Pair(currentRow, currentColumn - 1), // ←
                Pair(currentRow, currentColumn + 1), // →
                Pair(currentRow - 1, currentColumn), // ↑
                Pair(currentRow + 1, currentColumn)  // ↓
            )

            surroundings.forEach { adjacentPosition ->
                val adjacentRow = adjacentPosition.first
                val adjacentColumn = adjacentPosition.second

                if (isLand(adjacentRow, adjacentColumn)) {
                    grid[adjacentRow][adjacentColumn] = '0'

                    queue.add(Pair(adjacentRow, adjacentColumn))
                }
            }
        }
    }

    for (row in 0..<grid.size) {
        for (column in 0..<grid[row].size) {
            if (grid[row][column] == '1') {
                numberOfIslands++
                grid[row][column] = '0'
                bfs(row, column)
            }
        }
    }

    return numberOfIslands
}

