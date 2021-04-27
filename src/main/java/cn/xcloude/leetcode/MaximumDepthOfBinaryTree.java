package cn.xcloude.leetcode;

public class MaximumDepthOfBinaryTree {
  public int maxDepth(TreeNode root) {
    return maxDepth(root, 0);
  }

  private int maxDepth(TreeNode node, int depth) {
    if (node == null) {
      return depth;
    }

    depth++;
    return Math.max(maxDepth(node.left, depth), maxDepth(node.right, depth));
  }
}
