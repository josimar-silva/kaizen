package com.josimarsilva.leetcode.easy

import com.josimarsilva.leetcode.common.TreeNode
import java.util.*
import kotlin.math.max

/**
 * Given the root of a binary tree, return its maximum depth.
 *
 * A binary tree's maximum depth is the number of nodes
 * along the longest path from the root node down to the farthest leaf node.
 *
 * Constraints
 * - The number of nodes in the tree is in the range [0, 104].
 * - -100 <= Node.val <= 100
 *
 * Ref.: https://leetcode.com/problems/maximum-depth-of-binary-tree
 */
//Time: O(N) / Space O(N)
fun maxDepth(root: TreeNode?): Int {
    if (root == null) return 0

    val queue = LinkedList<TreeNode>().apply {
        add(root)
    }

    var depth = 0

    while (queue.isNotEmpty()) {
        (0..<queue.size).forEach { i ->
            val currentNode = queue.poll()
            if (currentNode.left != null) queue.add(currentNode.left!!)
            if (currentNode.right != null) queue.add(currentNode.right!!)
        }

        depth++
    }

    return depth
}

fun maxDepthUsingDFS(root: TreeNode?): Int {
    fun depth(root: TreeNode?): Int {
        if (root == null) return 0

        val leftDepth = depth(root.left)
        val rightDepth = depth(root.right)

        return 1 + max(leftDepth, rightDepth)
    }

    return depth(root)
}
