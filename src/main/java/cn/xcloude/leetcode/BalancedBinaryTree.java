package cn.xcloude.leetcode;

/**
 * leetcode 110
 * nowcoder JZ79
 * 判断二叉树是否为平衡二叉树
 */
public class BalancedBinaryTree {
  public boolean isBalanced(TreeNode root) {
    return depth(root) != -1;
  }

  private int depth(TreeNode root) {
    if (root == null) {
      return 0;
    }

    int left = depth(root.left);
    if (left == -1) {
      return -1;
    }

    int right = depth(root.right);
    if (right == -1) {
      return -1;
    }

    if (Math.abs(left - right) > 1) {
      return -1;
    }

    return Math.max(left, right) + 1;
  }
}
