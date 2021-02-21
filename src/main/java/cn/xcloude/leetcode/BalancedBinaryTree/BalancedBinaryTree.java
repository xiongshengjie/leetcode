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
    if (root == null) {
      return true;
    }

    return Math.abs(depth(root.left, 0) - depth(root.right, 0)) <= 1 && isBalanced(root.left) && isBalanced(root.right);
  }

  private int depth(TreeNode root, int depth) {
    if (root == null) {
      return depth;
    }

    depth++;
    return Math.max(depth(root.left, depth), depth(root.right, depth));
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
