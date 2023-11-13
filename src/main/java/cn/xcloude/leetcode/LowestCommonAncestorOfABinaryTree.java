package cn.xcloude.leetcode;

/**
 * leetcode 236
 * nowcoder JZ86
 * 二叉树的最近公共祖先
 */
public class LowestCommonAncestorOfABinaryTree {
  public TreeNode lowestCommonAncestor(TreeNode node, TreeNode p, TreeNode q) {
    if (node == null || node == p || node == q) {
      return node;
    }

    TreeNode left = lowestCommonAncestor(node.left, p, q);
    TreeNode right = lowestCommonAncestor(node.right, p, q);
    if (left != null && right != null) {
      return node;
    }

    return left == null ? right : left;
  }
}
