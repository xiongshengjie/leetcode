package cn.xcloude.leetcode.MinimumDepthOfBinaryTree;

class TreeNode {
  int val;
  TreeNode left;
  TreeNode right;

  TreeNode(int x) {
    val = x;
  }
}

public class MinimumDepthOfBinaryTree {
  public int run(TreeNode root) {
    if (root == null) {
      return 0;
    }

    if (root.left == null && root.right == null) {
      return 1;
    } else if (root.left == null) {
      return run(root.right) + 1;
    } else if (root.right == null) {
      return run(root.left) + 1;
    }

    return Math.min(run(root.left), run(root.right)) + 1;
  }
}
