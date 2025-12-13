package com.josimarsilva.leetcode.common;

import com.josimarsilva.leetcode.easy.diameterOfBinaryTree
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DiameterOfBinaryTreeTest {

    @ParameterizedTest
    @MethodSource("samples")
    fun `should return the diameter of a given binary tree`(root: TreeNode, expectedDiameter: Int) {
        assertThat(diameterOfBinaryTree(root)).isEqualTo(expectedDiameter)
    }

    private fun samples(): Stream<Arguments> = Stream.of(
        Arguments.of(
            TreeNode(1).apply {
                left = TreeNode(2).apply {
                    left = TreeNode(4)
                    right = TreeNode(5)
                }
                right = TreeNode(3)
            },
            3
        ),
        Arguments.of(
            TreeNode(1).apply {
                left = TreeNode(2)
            },
            1
        )
    )

}
