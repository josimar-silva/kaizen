package com.josimarsilva.leetcode.easy;

import com.josimarsilva.leetcode.common.TreeNode
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class InvertBinaryTreeTest {

    @ParameterizedTest
    @MethodSource("samples")
    fun `should invert a given binary tree`(root: TreeNode, inverted: TreeNode) {
        assertThat(invertTreeUsingDFS(root)).usingRecursiveComparison().isEqualTo(inverted)
        assertThat(invertTree(root)).usingRecursiveComparison().isEqualTo(inverted)
    }

    private fun samples(): Stream<Arguments> = Stream.of(
        Arguments.of(
            TreeNode(1).apply {
                left = TreeNode(2)
                right = TreeNode(3)
            },
            TreeNode(1).apply {
                left = TreeNode(3)
                right = TreeNode(2)
            }
        ),
        Arguments.of(
            TreeNode(1).apply {
                left = TreeNode(2).apply {
                    left = TreeNode(1)
                    right = TreeNode(3)
                }
                right = TreeNode(7).apply {
                    left = TreeNode(6)
                    right = TreeNode(9)
                }
            },
            TreeNode(1).apply {
                left = TreeNode(7).apply {
                    left = TreeNode(9)
                    right = TreeNode(6)
                }
                right = TreeNode(2).apply {
                    left = TreeNode(3)
                    right = TreeNode(1)
                }
            }
        ),
        Arguments.of(TreeNode(3), TreeNode(3)),
    )

}
