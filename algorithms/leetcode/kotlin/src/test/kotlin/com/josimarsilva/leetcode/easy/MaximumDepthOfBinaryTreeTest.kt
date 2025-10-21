package com.josimarsilva.leetcode.easy

import com.josimarsilva.leetcode.common.TreeNode
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MaximumDepthOfBinaryTreeTest {

    @ParameterizedTest
    @MethodSource("samples")
    fun `should return the maximum depth of a binary tree`(root: TreeNode, expectedDepth: Int) {
        assertThat(maxDepthUsingDFS(root)).isEqualTo(expectedDepth)
        assertThat(maxDepth(root)).isEqualTo(expectedDepth)
    }

    private fun samples(): Stream<Arguments> = Stream.of(
        Arguments.of(
            TreeNode(3).apply {
                left = TreeNode(9)
                right = TreeNode(20).apply {
                    left = TreeNode(15)
                    right = TreeNode(7)
                }
            },
            3
        ),
        Arguments.of(
            TreeNode(1).apply {
                right = TreeNode(2)
            },
            2
        ),
        Arguments.of(TreeNode(3), 1),
    )

}
