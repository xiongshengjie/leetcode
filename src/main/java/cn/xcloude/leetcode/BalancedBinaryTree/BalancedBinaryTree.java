package cn.xcloude.leetcode.BalancedBinaryTree;

class TreeNode {
  int val = 0;
  TreeNode left = null;
  TreeNode right = null;
}

/**
 * LC39
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

  public static void main(String[] args) {
    TreeNode root = new TreeNode();
    root.val = 1;

    TreeNode left = new TreeNode();
    left.val = 2;

    root.left = left;
    boolean result = new BalancedBinaryTree().isBalanced(root);
    System.out.println(result);
  }
}
