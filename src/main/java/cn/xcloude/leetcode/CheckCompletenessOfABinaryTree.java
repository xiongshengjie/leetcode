package cn.xcloude.leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * leetcode 958
 * 二叉树的完全性校验
 */
public class CheckCompletenessOfABinaryTree {
  public boolean isCompleteTree(TreeNode root) {
    if (root == null) {
      return true;
    }

    Queue<TreeNode> queue = new LinkedList<>();
    queue.add(root);
    boolean leaf = false;
    while (!queue.isEmpty()) {
      TreeNode node = queue.poll();
      if ((node.left == null && node.right != null)
          || (leaf && node.left != null)) {
        return false;
      }

      if (node.left != null) {
        queue.offer(node.left);
      }

      if (node.right != null) {
        queue.offer(node.right);
      } else {
        leaf = true;
      }
    }

    return true;
  }
}
