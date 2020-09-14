package cn.xcloude.leetcode.SumRootToLeafNumbers;

class TreeNode {
  int val = 0;
  TreeNode left = null;
  TreeNode right = null;
}

public class SumRootToLeafNumbers {
  public int sumNumbers(TreeNode root) {
    return sumNumbers(root, 0);
  }

  public int sumNumbers(TreeNode root, int prefix) {
    if (root == null) {
      return 0;
    }

    prefix = prefix * 10 + root.val;
    if (root.left == null && root.right == null) {
      return prefix;
    }

    return sumNumbers(root.left, prefix) + sumNumbers(root.right, prefix);
  }
}
