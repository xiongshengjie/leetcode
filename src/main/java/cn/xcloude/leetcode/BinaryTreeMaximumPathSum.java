package cn.xcloude.leetcode;

public class BinaryTreeMaximumPathSum {
  private Integer maxPath = Integer.MIN_VALUE;

  public int maxPathSum(TreeNode root) {
    maxPath(root);
    return maxPath;
  }

  private int maxPath(TreeNode node) {
    if (node == null) {
      return 0;
    }

    int maxLeft = Math.max(0, maxPath(node.left));
    int maxRight = Math.max(0, maxPath(node.right));
    maxPath = Math.max(maxPath, maxLeft + maxRight + node.val);
    return Math.max(maxLeft, maxRight) + node.val;
  }
}
