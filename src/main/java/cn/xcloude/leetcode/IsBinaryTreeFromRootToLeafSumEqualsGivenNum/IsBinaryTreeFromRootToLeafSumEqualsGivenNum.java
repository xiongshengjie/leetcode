package cn.xcloude.leetcode.IsBinaryTreeFromRootToLeafSumEqualsGivenNum;

class TreeNode {
  int val = 0;
  TreeNode left = null;
  TreeNode right = null;
}

/**
 * LC38
 * 二叉树中是否存在节点和为指定值的路径
 */
public class IsBinaryTreeFromRootToLeafSumEqualsGivenNum {
  public boolean hasPathSum(TreeNode root, int sum) {
    if (root == null) {
      return false;
    }

    return hasPathSum(root, 0, sum);
  }

  private boolean hasPathSum(TreeNode node, int result, int sum) {
    result += node.val;
    if (node.left == null && node.right == null && result == sum) {
      return true;
    }

    if (node.left != null && hasPathSum(node.left, result, sum)) {
      return true;
    }

    return node.right != null && hasPathSum(node.right, result, sum);
  }
}
