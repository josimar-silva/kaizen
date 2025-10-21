package com.josimarsilva.leetcode.easy

import com.josimarsilva.leetcode.common.TreeNode
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BalancedBinaryTreeTest {

    @ParameterizedTest
    @MethodSource("balancedSamples")
    fun `should return true given tree is height-balanced`(root: TreeNode) {
        assertThat(isBalanced(root)).isTrue
        assertThat(isBalancedNaive(root)).isTrue
    }

    private fun balancedSamples(): Stream<Arguments> = Stream.of(
        Arguments.of(
            TreeNode(3).apply {
                left = TreeNode(9)
                right = TreeNode(20).apply {
                    left = TreeNode(15)
                    right = TreeNode(7)
                }
            }
        ),
        Arguments.of(TreeNode(3)),
    )

    @ParameterizedTest
    @MethodSource("unbalancedSamples")
    fun `should return false given tree is unbalanced`(root: TreeNode) {
        assertThat(isBalanced(root)).isFalse
        assertThat(isBalancedNaive(root)).isFalse
    }

    private fun unbalancedSamples(): Stream<Arguments> = Stream.of(
        Arguments.of(
            TreeNode(1).apply {
                left = TreeNode(2)
                right = TreeNode(2).apply {
                    left = TreeNode(3)
                    right = TreeNode(3).apply {
                        left = TreeNode(4)
                        right = TreeNode(4).apply {
                            left = TreeNode(5)
                            right = TreeNode(5)
                        }
                    }
                }
            }
        ),
        Arguments.of(
            TreeNode(1).apply {
                left = TreeNode(1)
                right = TreeNode(2).apply {
                    left = TreeNode(3).apply {
                        left = TreeNode(4)
                    }
                }
            }
        ),
    )

}
