package com.josimarsilva.leetcode.easy

import com.josimarsilva.leetcode.common.TreeNode
import kotlin.math.abs
import kotlin.math.max

/**
 * Given a binary tree, determine if it is height-balanced.
 * Height-balanced: a binary tree in which the height of the left
 * and the right subtree of any node differ by not more than 1.
 *
 * Constraints
 * - The number of nodes in the tree is in the range [0, 5000].
 * - -104 <= Node.val <= 104
 *
 * Ref.: https://leetcode.com/problems/balanced-binary-tree
 */
fun isBalanced(root: TreeNode?): Boolean {
    fun height(root: TreeNode?): Int {
        if (root == null) return 0

        val leftHeight = height(root.left)
        val rightHeight = height(root.right)

        if (leftHeight == -1 || rightHeight == -1 || abs(leftHeight - rightHeight) > 1) return -1

        return 1 + max(leftHeight, rightHeight)
    }

    return height(root) >= 0
}

//Time O(N²) / Space O(N)
fun isBalancedNaive(root: TreeNode?): Boolean {
    if (root == null) return true
    if (root.left == null && root.right == null) return true

    val leftHeight = height(root.left)
    val rightHeight = height(root.right)

    if (abs(leftHeight - rightHeight) > 1) return false

    return isBalancedNaive(root.left) && isBalancedNaive(root.right)
}

fun height(root: TreeNode?): Int {
    if (root == null) return 0

    return 1 + max(height(root.left), height(root.right))
}
