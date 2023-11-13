package cn.xcloude.leetcode;

/**
 * leetcode 235
 * nowcoder JZ68
 * 二叉搜索树的最近公共祖先
 */
public class LowestCommonAncestorOfABinarySearchTree {
  public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    if (root == null || p == null || q == null) {
      return null;
    }

    if (p.val > q.val) {
      TreeNode temp = p;
      p = q;
      q = temp;
    }
    return lowestCommonAncestor0(root, p, q);
  }

  private TreeNode lowestCommonAncestor0(TreeNode node, TreeNode lower, TreeNode greater) {
    if (node == null) {
      return null;
    }

    if (node.val < lower.val) {
      return lowestCommonAncestor0(node.right, lower, greater);
    }

    if (node.val > greater.val) {
      return lowestCommonAncestor0(node.left, lower, greater);
    }

    return node;
  }
}
