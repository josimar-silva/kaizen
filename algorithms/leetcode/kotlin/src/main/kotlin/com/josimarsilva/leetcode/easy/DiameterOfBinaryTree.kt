package com.josimarsilva.leetcode.easy

import com.josimarsilva.leetcode.common.TreeNode
import kotlin.math.max

/**
 * Given the root of a binary tree, return the length of the diameter of the tree.
 *
 * The diameter of a binary tree is the length of the longest path between any two nodes in a tree.
 * This path may or may not pass through the root.
 *
 * The length of a path between two nodes is represented by the number of edges between them.
 *
 * Constraints:
 * - The number of nodes in the tree is in the range [1, 104].
 * - -100 <= Node.val <= 100
 *
 * Ref.: https://leetcode.com/problems/diameter-of-binary-tree
 */
//Time: O(N) Space: O(N)
fun diameterOfBinaryTree(root: TreeNode?): Int {
    if (root == null) return 0

    var maxDiameter = 0

    fun heightOf(root: TreeNode?): Int {
        if(root == null) return 0

        val leftHeight = heightOf(root.left)
        val rightHeight = heightOf(root.right)

        maxDiameter = maxOf(maxDiameter, leftHeight + rightHeight)

        return 1 + maxOf(leftHeight, rightHeight)
    }

    heightOf(root)

    return maxDiameter
}