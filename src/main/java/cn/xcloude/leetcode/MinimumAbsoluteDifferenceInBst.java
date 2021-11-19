package cn.xcloude.leetcode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * leetcode 530
 * 二叉搜索树的最小绝对差
 */
public class MinimumAbsoluteDifferenceInBst {
  private int preValue = (int) -Math.pow(10, 5);
  private int result = Integer.MAX_VALUE;

  public int getMinimumDifference(TreeNode root) {
    if (root == null) {
      return 0;
    }

    inorderTraversalRecursion(root);
    return result;
  }

  // 递归
  private int inorderTraversalIteratively(TreeNode root) {
    Deque<TreeNode> stack = new LinkedList<>();
    stack.push(root);
    TreeNode node = root.left;
    int preValue = (int) -Math.pow(10, 5), result = Integer.MAX_VALUE;
    while (node != null || !stack.isEmpty()) {
      while (node != null) {
        stack.push(node);
        node = node.left;
      }

      node = stack.pop();

      int difference = node.val - preValue;
      if (difference < result) {
        result = difference;
      }
      preValue = node.val;

      node = node.right;
    }

    return result;
  }

  // 迭代
  private void inorderTraversalRecursion(TreeNode node) {
    if (node == null) {
      return;
    }

    inorderTraversalRecursion(node.left);

    int difference = node.val - preValue;
    if (difference < result) {
      result = difference;
    }
    preValue = node.val;

    inorderTraversalRecursion(node.right);
  }
}
