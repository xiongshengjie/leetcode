package cn.xcloude.leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * LC48
 * 判断二叉树是否对称
 */
public class SymmetricTree {
  public boolean isSymmetric(TreeNode root) {
    return isSymmetricRecursion(root);
  }

  // 递归实现
  private boolean isSymmetricRecursion(TreeNode root) {
    if (root == null) {
      return true;
    }

    return isSymmetricRecursion(root.left, root.right);
  }

  private boolean isSymmetricRecursion(TreeNode left, TreeNode right) {
    if (left == null) {
      return right == null;
    }

    if (right == null) {
      return false;
    }

    if (left.val != right.val) {
      return false;
    }

    return isSymmetricRecursion(left.left, right.right)
        && isSymmetricRecursion(left.right, right.left);
  }

  // 迭代实现
  private boolean isSymmetricIteratively(TreeNode root) {
    if (root == null) {
      return true;
    }

    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(root.left);
    queue.offer(root.right);

    while (!queue.isEmpty()) {
      int size = queue.size();
      for (int i = 0; i < size; i++) {
        TreeNode left = queue.poll();
        TreeNode right = queue.poll();

        if (left == null && right == null) {
          continue;
        }

        if ((left == null || right == null) || left.val != right.val) {
          return false;
        }

        queue.offer(left.left);
        queue.offer(right.right);
        queue.offer(left.right);
        queue.offer(right.left);
      }
    }

    return true;
  }
}
