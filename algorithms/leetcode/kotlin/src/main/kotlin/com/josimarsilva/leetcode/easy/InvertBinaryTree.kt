package com.josimarsilva.leetcode.easy

import com.josimarsilva.leetcode.common.TreeNode
import java.util.*

/**
 * Given the root of a binary tree, invert the tree, and return its root.
 *
 * Constraints:
 *
 * - The number of nodes in the tree is in the range [0, 100].
 * - -100 <= Node.val <= 100
 *
 * Ref.: https://leetcode.com/problems/invert-binary-tree/description
 */
//Time: O(N) / Space O(N)
fun invertTree(root: TreeNode?): TreeNode? {
    if (root == null) return root
    if (root.left === null && root.right === null) return root

    val queue = LinkedList<TreeNode>().apply { add(root) }

    while (queue.isNotEmpty()) {
        (0..<queue.size).forEach { i ->
            val currentNode = queue.poll()
            val left = currentNode.left
            val right = currentNode.right

            if (left != null) queue.add(left)
            if (right != null) queue.add(right)

            currentNode.right = left
            currentNode.left = right
        }
    }

    return root
}

//Time: O(N) / Space O(N)
fun invertTreeUsingDFS(root: TreeNode?): TreeNode? {
    fun invert(head: TreeNode?): TreeNode? {
        if (head == null) return head
        if (head.left === null && head.right === null) return head

        val left = invert(head.left)
        val right = invert(head.right)

        head.left = right
        head.right = left

        return head
    }

    return invert(root)
}
